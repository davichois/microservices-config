package com.carro.service.controller;

import com.carro.service.entity.Carro;
import com.carro.service.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carro")
public class CarroController {

    @Autowired
    private CarroService carroService;


    @GetMapping
    public ResponseEntity<List<Carro>> listCarros(){
        List<Carro> carros = carroService.getAll();

        if (carros.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(carros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> getCarro(@PathVariable("id") int id){
        Carro carro = carroService.getCarroById(id);

        if (carro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }

    @PostMapping
    public ResponseEntity<Carro> saveCarro(@RequestBody Carro carro){
        Carro newCarro = carroService.save(carro);
        return ResponseEntity.ok(newCarro);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Carro>> listarCarrosUsuarioId(@PathVariable("usuarioId") int id){
        List<Carro> carros = carroService.byUsuarioId(id);

        if (carros.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(carros);
    }

}
