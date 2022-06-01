package com.usuario.service.feignClients;

import com.usuario.service.models.Carro;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "carro-service", url = "http://localhost:8002", path = "/carro")
public interface CarroFeignClient {

    @PostMapping()
    Carro save(@RequestBody Carro carro);

    @GetMapping("/usuario/{usuarioId}")
    List<Carro> getListCarro(@PathVariable("usuarioId") int usuarioId);

}
