package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/abonos")
public class AbonoController {

    private final AbonoService abonoService;
    private final VehiculoService vehiculoService;
    private final TarifaService tarifaService;

    @Autowired //Quitar constructor y poner el Autowired en los atributos?
    public AbonoController(AbonoService abonoService, VehiculoService vehiculoService, TarifaService tarifaService) {
        this.abonoService = abonoService;
        this.vehiculoService = vehiculoService;
        this.tarifaService = tarifaService;
    }

    @GetMapping
    public ResponseEntity<List<Abono>> getAllAbonos() {
        List<Abono> abonos = abonoService.getAllAbonos();
        return ResponseEntity.ok(abonos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Abono> getAbonoById(@PathVariable UUID id) {
        Abono abono = abonoService.getAbonoById(id);
        if (abono != null) {
            return ResponseEntity.ok(abono);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*¿Si los abonos se crean cuando se realiza un pago, llamando directamente a service, para qué sirve esto? */
    @PostMapping("/anual")
    public ResponseEntity<Void> addAbonoAnual(@RequestBody AbonoDTO abonoDTO) {
        Vehiculo vehiculo = vehiculoService.getVehiculoByMatricula(abonoDTO.getMatricula());
        Tarifa tarifa = tarifaService.getTarifaByNombre(abonoDTO.getNombreTarifa());
        if(vehiculo == null) {
            vehiculo = new Vehiculo(abonoDTO.getMatricula());
            vehiculoService.addVehiculo(vehiculo);
        }

        Abono abono = new AbonoAnual(tarifa.getCosteAbonoAnual(), vehiculo);
        abonoService.addAbono(abono);

        vehiculo.setAbonoActual(abono);
        vehiculoService.updateVehiculoAbono(vehiculo, abono);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/mensual")
    public ResponseEntity<Void> addAbonoMensual(@RequestBody AbonoDTO abonoDTO) {

        Vehiculo vehiculo = vehiculoService.getVehiculoByMatricula(abonoDTO.getMatricula());
        Tarifa tarifa = tarifaService.getTarifaByNombre(abonoDTO.getNombreTarifa());
        if(vehiculo == null) {
            vehiculo = new Vehiculo(abonoDTO.getMatricula());
            vehiculoService.addVehiculo(vehiculo);
        }

        Abono abono = new AbonoMensual(tarifa.getCosteAbonoMensual(), vehiculo);
        abonoService.addAbono(abono);

        vehiculo.setAbonoActual(abono);
        vehiculoService.updateVehiculoAbono(vehiculo, abono);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/trimestral")
    public ResponseEntity<Void> addAbonoTrimestral(@RequestBody AbonoDTO abonoDTO) {
        
        Vehiculo vehiculo = vehiculoService.getVehiculoByMatricula(abonoDTO.getMatricula());
        Tarifa tarifa = tarifaService.getTarifaByNombre(abonoDTO.getNombreTarifa());
        if(vehiculo == null) {
            vehiculo = new Vehiculo(abonoDTO.getMatricula());
            vehiculoService.addVehiculo(vehiculo);
        }

        Abono abono = new AbonoTrimestral(tarifa.getCosteAbonoTrimestral(), vehiculo);
        abonoService.addAbono(abono);

        vehiculo.setAbonoActual(abono);
        vehiculoService.updateVehiculoAbono(vehiculo, abono);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/put-{id}")
    public ResponseEntity<Void> updateAbono(@PathVariable UUID id, @RequestBody Abono abono) {
        Abono abonoToUpdate = abonoService.getAbonoById(id);
        if (abonoToUpdate != null) {
            abonoService.update(abonoToUpdate);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbono(@PathVariable UUID id) {
        Abono abono = abonoService.getAbonoById(id);
        if (abono != null) {
            abonoService.delete(abono);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
