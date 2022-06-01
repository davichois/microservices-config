package com.moto.service.controller;

import com.moto.service.entity.Moto;
import com.moto.service.service.MotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/moto")
public class MotoController {

    @Autowired
    private MotoService motoService;


    @GetMapping
    public ResponseEntity<List<Moto>> listMotos(){
        List<Moto> motos = motoService.getAll();

        if (motos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(motos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moto> getMoto(@PathVariable("id") int id){
        Moto carro = motoService.getMotoById(id);

        if (carro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(carro);
    }

    @PostMapping
    public ResponseEntity<Moto> saveMoto(@RequestBody Moto moto){
        Moto newMoto = motoService.save(moto);
        return ResponseEntity.ok(newMoto);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Moto>> listarMotosUsuarioId(@PathVariable("usuarioId") int id){
        List<Moto> motos = motoService.byUsuarioId(id);

        if (motos.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(motos);
    }


}
