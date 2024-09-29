package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BBDDEntradaRepository implements EntradaRepository  {

    @Autowired
    private EntradaAdapter entradaAdapter;

    public List<Entrada> getAll(){
        List<EntradaRelacional> Entradas = entradaAdapter.findAll();
        List<Entrada> EntradasNormales = new ArrayList<Entrada>();
        for(EntradaRelacional EntradaR : Entradas){
            Entrada ent = EntradaR.toEntrada();
            ent.setVehiculo(EntradaR.getVehiculo().toVehiculo());
            EntradasNormales.add(EntradaR.toEntrada());
        }
        return EntradasNormales;
    }
    
    public void add(Entrada e) {
        EntradaRelacional entradaR = e.toEntradaRelacional();
        entradaR.setVehiculo(e.getVehiculo().toVehiculoRelacional());
        entradaAdapter.save(entradaR);  
    }

    public void update(Entrada e){
        EntradaRelacional entradaR = e.toEntradaRelacional();
        entradaR.setVehiculo(e.getVehiculo().toVehiculoRelacional());
        entradaAdapter.save(entradaR);  
    }

    public void delete(Entrada e){
        EntradaRelacional entradaR = e.toEntradaRelacional();
        entradaR.setVehiculo(e.getVehiculo().toVehiculoRelacional());
        entradaAdapter.delete(entradaR); 
    }

    public List<Entrada> getEntradasByfecha(LocalDate f) {
        //Preparando la fecha para que sea compatible con la base de datos
        LocalDateTime fecha = f.atStartOfDay();
        LocalDateTime fecha2 = f.atTime(23,59,59);



        List<EntradaRelacional> Entradas = entradaAdapter.findByfechaBetween(fecha, fecha2);

        List<Entrada> EntradasNormales = new ArrayList<Entrada>();
        for(EntradaRelacional EntradaR : Entradas){
            Entrada ent = EntradaR.toEntrada();
            Vehiculo v = EntradaR.getVehiculo().toVehiculo();
            
            //Operaciones para completar el vehiculo
            if(EntradaR.getVehiculo().getAbono() != null)v.setAbonoActual(EntradaR.getVehiculo().getAbono().toAbono());
            if(EntradaR.getVehiculo().getTicket() != null)v.setTicket(EntradaR.getVehiculo().getTicket().toTicket());
            if(EntradaR.getVehiculo().getConductor()!= null)v.setConductor(EntradaR.getVehiculo().getConductor().toCliente());

            ent.setVehiculo(v);

            

            EntradasNormales.add(ent);
        }
        return EntradasNormales;

    }

    @Override
    public List<Entrada> getEntradasByVehiculo(String mat) {

        VehiculoRelacional vehiculo = new VehiculoRelacional(mat);
        List<EntradaRelacional> Entradas = entradaAdapter.findByVehiculo(vehiculo);
        List<Entrada> EntradasNormales = new ArrayList<Entrada>();

        for(EntradaRelacional EntradaR : Entradas){
            Entrada ent = EntradaR.toEntrada();
            Vehiculo v = EntradaR.getVehiculo().toVehiculo();
            
            //Operaciones para completar el vehiculo
            if(EntradaR.getVehiculo().getAbono() != null)v.setAbonoActual(EntradaR.getVehiculo().getAbono().toAbono());
            if(EntradaR.getVehiculo().getTicket() != null)v.setTicket(EntradaR.getVehiculo().getTicket().toTicket());
            if(EntradaR.getVehiculo().getConductor()!= null)v.setConductor(EntradaR.getVehiculo().getConductor().toCliente());

            ent.setVehiculo(v);

            

            EntradasNormales.add(ent);
        }
        return EntradasNormales;
    }

    public List<Entrada> getEntradasByVehiculoAndFecha(String mat, LocalDate f) {
        
        //Preparando la fecha para que sea compatible con la base de datos
        LocalDateTime fecha = f.atStartOfDay();
        LocalDateTime fecha2 = f.atTime(23,59,59);

        VehiculoRelacional vehiculo = new VehiculoRelacional(mat);

        List<EntradaRelacional> Entradas = entradaAdapter.findByVehiculoAndFechaBetween(vehiculo,fecha, fecha2);
        List<Entrada> EntradasNormales = new ArrayList<Entrada>();

        for(EntradaRelacional EntradaR : Entradas){
            Entrada ent = EntradaR.toEntrada();
            Vehiculo v = EntradaR.getVehiculo().toVehiculo();
            
            //Operaciones para completar el vehiculo
            if(EntradaR.getVehiculo().getAbono() != null)v.setAbonoActual(EntradaR.getVehiculo().getAbono().toAbono());
            if(EntradaR.getVehiculo().getTicket() != null)v.setTicket(EntradaR.getVehiculo().getTicket().toTicket());
            if(EntradaR.getVehiculo().getConductor()!= null)v.setConductor(EntradaR.getVehiculo().getConductor().toCliente());

            ent.setVehiculo(v);

            

            EntradasNormales.add(ent);
        }
        return EntradasNormales;
    }
}


