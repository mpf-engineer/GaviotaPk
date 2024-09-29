package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tarifas")
public class TarifaController {

    @Autowired
    private final TarifaService tarifaService;

    public TarifaController(TarifaService tarifaService) {
        this.tarifaService = tarifaService;
    }


    @GetMapping
    public ResponseEntity<List<Tarifa>> getAllTarifas() {
        List<Tarifa> tarifas = tarifaService.getAllTarifas();
        return ResponseEntity.ok(tarifas);
    }

    @PostMapping("/Tarifa-nueva")
    public ResponseEntity<Void> addTarifa(@RequestBody TarifaDTO tarifa) {
        Tarifa nuevaTarifa = new Tarifa(tarifa.getNombre(), tarifa.getCostePorHora(), tarifa.getCostePorDia(), 
                        tarifa.getCosteAbonoMensual(), tarifa.getCosteAbonoTrimestral(), tarifa.getCosteAbonoAnual());
        tarifaService.addTarifa(nuevaTarifa);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* Implementar el cambio en algún parámetro de tarifa
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTarifa(@PathVariable UUID id, @RequestBody Tarifa tarifa) {
        Tarifa tarifaToUpdate = tarifaService.getTarifaById(id);
        if (tarifaToUpdate != null) {
            tarifaService.updateTarifa(tarifaToUpdate, tarifa);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarifa(@PathVariable UUID id) {
        Tarifa tarifa = tarifaService.getTarifaById(id);
        if (tarifa != null) {
            tarifaService.deleteTarifa(tarifa);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
