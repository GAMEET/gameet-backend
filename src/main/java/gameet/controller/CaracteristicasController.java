package gameet.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import gameet.entity.Caracteristicas;

@RestController
public class CaracteristicasController {

    @GetMapping("/caracteristicas")
    public List<Caracteristicas> obtenerTodasLasCaracteristicas() {
        return Arrays.asList(Caracteristicas.values());
    }
}
