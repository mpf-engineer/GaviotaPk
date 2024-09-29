package es.uca.GaviotaPK.GaviotaParking;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entradas")
public class EntradaController {
    private EntradaService EntradaService;
    public EntradaController(EntradaService cs){
        this.EntradaService = cs;
    }
    @GetMapping("/Entradas")
    public List<Entrada> getEntradas(@RequestBody MovimientoDTO entradaDTO){
        if(entradaDTO.getMatricula() != null && entradaDTO.getFecha() != null){
            LocalDate fecha = LocalDate.parse(entradaDTO.getFecha());
            return EntradaService.getEntradasByVehiculoAndFecha(entradaDTO.getMatricula(), fecha);

        }else if(entradaDTO.getMatricula() != null){
            return EntradaService.getEntradasByVehiculo(entradaDTO.getMatricula());

        }else{
            LocalDate fecha = LocalDate.parse(entradaDTO.getFecha());
            return EntradaService.getEntradasByFecha(fecha);
        }
        
    }
    @PutMapping("/Entradas-nuevo")
    public void addEntrada(@RequestParam String mat, @RequestParam LocalDateTime f){
        addEntrada(mat, f);
    }
}
