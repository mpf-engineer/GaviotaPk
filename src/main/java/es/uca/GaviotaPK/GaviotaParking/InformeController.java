package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDate;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/informes")
public class InformeController {
    private InformeService informeService;

    public InformeController(InformeService is){
        this.informeService = is;
    }


    @PostMapping("/InformeES")
    public ResponseEntity<List<Movimiento>> generarInformeES(@RequestBody InformeESDTO informeDTO){
        LocalDate fecha = null;
        if(informeDTO.getFecha() != null && (!"null".equals(informeDTO.getFecha()))){
            fecha = LocalDate.parse(informeDTO.getFecha());
            //La fecha no puede ser en el futuro
            if(fecha.isAfter(LocalDate.now())) return ResponseEntity.badRequest().build();
        }
        
        String matricula = informeDTO.getMatricula();
        if(informeDTO.getMatricula() == null || "null".equals(informeDTO.getMatricula())){
            matricula = null;
        } 
        
        List<Movimiento> movimientos = informeService.generarInformeES(fecha, matricula);
       

        
        return ResponseEntity.ok(movimientos);
    }


    @PostMapping("/InformeIngresos")
    public ResponseEntity<List<Pago>> generarInformeIngresos(@RequestBody InformeIngresosDTO informeDTO){
        LocalDate fechaInicio = null;
        LocalDate fechaFin = null;

        if(!fechanula(informeDTO.getFechaIni())) fechaInicio = LocalDate.parse(informeDTO.getFechaIni());
        if(!fechanula(informeDTO.getFechaFin())) fechaFin = LocalDate.parse(informeDTO.getFechaFin());

        //Comprobación de fechas cruzadas
        if(!fechanula(informeDTO.getFechaIni()) && !fechanula(informeDTO.getFechaFin())){
            if (fechaInicio.isAfter(fechaFin)) {
                return ResponseEntity.badRequest().build();
            }
        }
        List<Pago> pagos = informeService.generarInformeIngresos(fechaInicio, fechaFin);
        return ResponseEntity.ok(pagos); 
    }


    private boolean fechanula(String fecha){
        return fecha == null || "null".equals(fecha);
    }   
}
