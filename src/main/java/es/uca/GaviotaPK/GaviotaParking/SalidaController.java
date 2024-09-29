package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController("/salidas")
public class SalidaController {
    private SalidaService SalidaService;
    public SalidaController(SalidaService cs){
        this.SalidaService = cs;
    }
    @GetMapping("/Salidas")
    public List<Salida> getSalidas(@RequestBody MovimientoDTO salidaDTO){

        if(salidaDTO.getMatricula() != null && salidaDTO.getFecha() != null){
            LocalDate fecha = LocalDate.parse(salidaDTO.getFecha());
            return SalidaService.getSalidasByVehiculoAndFecha(salidaDTO.getMatricula(), fecha);

        }else if(salidaDTO.getMatricula() != null){
            return SalidaService.getSalidasByVehiculo(salidaDTO.getMatricula());

        }else{
            LocalDate fecha = LocalDate.parse(salidaDTO.getFecha());
            return SalidaService.getSalidasByFecha(fecha);
        }
    }
    @PutMapping("/Salidas-nuevo")
    public void addSalida(@RequestParam String mat, @RequestParam LocalDateTime f){
        addSalida(mat, f);
    }
}
