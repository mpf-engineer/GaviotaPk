package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BBDDSalidaRepository implements SalidaRepository  {

    @Autowired
    private SalidaAdapter SalidaAdapter;

    public BBDDSalidaRepository(SalidaAdapter ea){
        this.SalidaAdapter = ea;
    }

    public List<Salida> getAll(){
        List<SalidaRelacional> Salidas = SalidaAdapter.findAll();
        List<Salida> SalidasNormales = new ArrayList<Salida>();
        for(SalidaRelacional SalidaR : Salidas){
            Salida ent = SalidaR.toSalida();
            ent.setVehiculo(SalidaR.getVehiculo().toVehiculo());
            SalidasNormales.add(SalidaR.toSalida());
        }
        return SalidasNormales;
    }
    
    public void add(Salida e) {
        SalidaRelacional SalidaR = e.toSalidaRelacional();
        SalidaR.setVehiculo(e.getVehiculo().toVehiculoRelacional());
        SalidaAdapter.save(SalidaR);  
    }

    public void update(Salida e){
        SalidaRelacional SalidaR = e.toSalidaRelacional();
        SalidaR.setVehiculo(e.getVehiculo().toVehiculoRelacional());
        SalidaAdapter.save(SalidaR);  
    }

    public void delete(Salida e){
        SalidaRelacional SalidaR = e.toSalidaRelacional();
        SalidaR.setVehiculo(e.getVehiculo().toVehiculoRelacional());
        SalidaAdapter.delete(SalidaR); 
    }

    public List<Salida> getSalidasByfecha(LocalDate f) {
        //Preparando la fecha para que sea compatible con la base de datos
        LocalDateTime fecha = f.atStartOfDay();
        LocalDateTime fecha2 = f.atTime(23,59,59);


        List<SalidaRelacional> Salidas = SalidaAdapter.findByfechaBetween(fecha,fecha2);
        List<Salida> SalidasNormales = new ArrayList<Salida>();
        for(SalidaRelacional SalidaR : Salidas){
            Salida sal = SalidaR.toSalida();
            Vehiculo v = SalidaR.getVehiculo().toVehiculo();

            
            //Operaciones para completar el vehiculo
            if(SalidaR.getVehiculo().getAbono() != null)v.setAbonoActual(SalidaR.getVehiculo().getAbono().toAbono());
            if(SalidaR.getVehiculo().getTicket() != null)v.setTicket(SalidaR.getVehiculo().getTicket().toTicket());
            if(SalidaR.getVehiculo().getConductor()!= null)v.setConductor(SalidaR.getVehiculo().getConductor().toCliente());

            sal.setVehiculo(v);
        
            SalidasNormales.add(sal);
        }
        return SalidasNormales;

    }

    @Override
    public List<Salida> getSalidasByVehiculo(String mat) {

        VehiculoRelacional vehiculo = new VehiculoRelacional(mat);
        List<SalidaRelacional> Salidas = SalidaAdapter.findByvehiculo(vehiculo);
        List<Salida> SalidasNormales = new ArrayList<Salida>();
        for(SalidaRelacional SalidaR : Salidas){
            Salida sal = SalidaR.toSalida();
            Vehiculo v = SalidaR.getVehiculo().toVehiculo();

            
            //Operaciones para completar el vehiculo
            if(SalidaR.getVehiculo().getAbono() != null)v.setAbonoActual(SalidaR.getVehiculo().getAbono().toAbono());
            if(SalidaR.getVehiculo().getTicket() != null)v.setTicket(SalidaR.getVehiculo().getTicket().toTicket());
            if(SalidaR.getVehiculo().getConductor()!= null)v.setConductor(SalidaR.getVehiculo().getConductor().toCliente());

            sal.setVehiculo(v);
        
            SalidasNormales.add(sal);
        }
        return SalidasNormales;
    }

    public List<Salida> getSalidasByVehiculoAndFecha(String mat, LocalDate f) {
        //Preparando la fecha para que sea compatible con la base de datos
        LocalDateTime fecha = f.atStartOfDay();
        LocalDateTime fecha2 = f.atTime(23,59,59);

        VehiculoRelacional vehiculo = new VehiculoRelacional(mat);
        
        List<SalidaRelacional> Salidas = SalidaAdapter.findByVehiculoAndFechaBetween(vehiculo,fecha, fecha2);
        List<Salida> SalidasNormales = new ArrayList<Salida>();

        for(SalidaRelacional SalidaR : Salidas){
            Salida sal = SalidaR.toSalida();
            Vehiculo v = SalidaR.getVehiculo().toVehiculo();

            
            //Operaciones para completar el vehiculo
            if(SalidaR.getVehiculo().getAbono() != null)v.setAbonoActual(SalidaR.getVehiculo().getAbono().toAbono());
            if(SalidaR.getVehiculo().getTicket() != null)v.setTicket(SalidaR.getVehiculo().getTicket().toTicket());
            if(SalidaR.getVehiculo().getConductor()!= null)v.setConductor(SalidaR.getVehiculo().getConductor().toCliente());

            sal.setVehiculo(v);
        
            SalidasNormales.add(sal);
        }
        return SalidasNormales;
    }
}


