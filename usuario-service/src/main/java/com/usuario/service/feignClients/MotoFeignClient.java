package com.usuario.service.feignClients;

import com.usuario.service.models.Moto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "moto-service", url = "http://localhost:8003", path = "/moto")
public interface MotoFeignClient {

    @PostMapping()
    Moto save(@RequestBody Moto carro);

    @GetMapping("/usuario/{usuarioId}")
    List<Moto> getListMotos(@PathVariable("usuarioId") int usuarioId);
}
