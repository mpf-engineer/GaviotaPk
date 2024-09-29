package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingService{

    private String nombre;
    private String dirPostal;
    private int plazasTotales;
    private int plazasDisp;
    private EntradaService entradaService;
    private SalidaService salidaService;
    private VehiculoService vehiculoService;
    private Barrera barreraE;
    private Barrera barreraS;
    private Impresora impresora;
    private Lector lector;


    //Constructor
    @Autowired
    public ParkingService(EntradaService e, SalidaService s,
                            VehiculoService v, PagoService p, TicketService ts){
        
        entradaService = e;
        salidaService = s;
        vehiculoService = v;
        barreraE = new BarreraEntrada();
        barreraS = new BarreraSalida();
        impresora = new Impresora(ts);
        lector = new Lector();
        plazasDisp = 100;
        plazasTotales = 100;

    };
        
    //Observadores
    public String getNombre(){return nombre;};
    public String getDir(){return dirPostal;};
    public int getPlazasDisp(){return plazasDisp;};
    public int getPlazasTotales(){return plazasTotales;};

    public void setNombre(String n){nombre = n;};
    public void setDir(String d){dirPostal = d;};
    public void setPlazasDisp(int p){plazasDisp = p;};
    public void setPlazasTotales(int pt){plazasTotales = pt;};


    //Métodos sobre funcionalidades

    public void entraCoche(String matricula){
        Vehiculo coche;
        //Paso número 1: Comprobar si el vehículo existe
        if(!VehiculoRegistrado(matricula)){
            coche = new Vehiculo(matricula);
            vehiculoService.addVehiculo(coche);
        }
        else{
            coche = vehiculoService.getVehiculoByMatricula(matricula);
        }

        //Comprobamos si el coche ya está dentro del parking
        if(coche.estaEnParking()){
            throw new IllegalArgumentException("El coche no puede entrar porque ya está dentro del parking");
        }

        if(plazasDisp == 0){
            throw new IllegalArgumentException("El coche no puede entrar porque no hay plazas disponibles");
        }

        //Comprobamos si hace falta sacar un ticket porque el coche no es abonado
        if(!coche.estaAbonado()){
            coche.setTicket(impresora.imprimeTicket(coche));
            vehiculoService.updateVehiculo(coche);
        }
        

        //Añadir al repo
        entradaService.addEntrada(coche.getMatricula(), LocalDateTime.now()); 
        
        coche.setEnParking(true);
        vehiculoService.updateVehiculoEnParking(coche);
      
        barreraE.abrirBarrera();
        plazasDisp--;
        barreraE.cerrarBarrera();
    };

        
    public void saleCoche(String matricula){ 
        Vehiculo coche = vehiculoService.getVehiculoByMatricula(matricula);
        if(!coche.estaAbonado()){
            boolean esValido = lector.leerTicket(coche);
            if(!esValido){
                System.out.println("Prohibido el paso!");
                throw new IllegalArgumentException("El coche no puede salir porque no se ha pagado el ticket");
            }
        }
        Salida salida = new Salida(coche, LocalDateTime.now());
        //Añadir al repo
        salidaService.addSalida(salida);

        coche.setEnParking(false);
        vehiculoService.updateVehiculoEnParking(coche);

        barreraS.abrirBarrera();
        plazasDisp++;
        barreraS.cerrarBarrera();
    };

    private boolean VehiculoRegistrado(String matricula){
            Vehiculo v = vehiculoService.getVehiculoByMatricula(matricula);
            if(v == null){
                return false;
            }
            else return true;
    }
}