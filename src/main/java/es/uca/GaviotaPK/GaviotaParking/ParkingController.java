package es.uca.GaviotaPK.GaviotaParking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/parking")
public class ParkingController {
    @Autowired
    private ParkingService parkingService;


    @PutMapping("/EntraCoche")
    public void entraCoche(@RequestBody VehiculoDTO vehiculo){
            parkingService.entraCoche(vehiculo.getMatricula());
    }

    @PutMapping("/SaleCoche")
    public void saleCoche(@RequestBody VehiculoDTO vehiculo){
        parkingService.saleCoche(vehiculo.getMatricula());
    }
}
