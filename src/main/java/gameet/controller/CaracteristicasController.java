package gameet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gameet.service.CaracteristicasService;

@RestController
public class CaracteristicasController {
	
	@Autowired
	private CaracteristicasService caracteristicasServ;

    @GetMapping("/caracteristicas")
    public List<String> obtenerTodasLasCaracteristicas() {
        try {
            return caracteristicasServ.getAllCaracteristicas();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
