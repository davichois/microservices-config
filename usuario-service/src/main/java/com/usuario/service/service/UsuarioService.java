package com.usuario.service.service;

import com.usuario.service.entity.Usuario;
import com.usuario.service.feignClients.CarroFeignClient;
import com.usuario.service.feignClients.MotoFeignClient;
import com.usuario.service.models.Carro;
import com.usuario.service.models.Moto;
import com.usuario.service.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CarroFeignClient carroFeignClient;

    @Autowired
    private MotoFeignClient motoFeignClient;



    public List<Usuario> getAll(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(int id){
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario){
        Usuario newUser = usuarioRepository.save(usuario);
        return newUser;
    }



    // RestTemplate
    public List<Carro> getCarros(int usuarioId){
        List<Carro> carros = restTemplate.getForObject("http://localhost:8002/carro/usuario/" + usuarioId, List.class);
        return carros;
    }

    public List<Moto> getMotos(int usuarioId){
        List<Moto> motos = restTemplate.getForObject("http://localhost:8003//usuario/" + usuarioId, List.class);
        return motos;
    }

    // Feign Client
    public Carro saveCarro(int usuarioId, Carro carro){
        carro.setUsuarioId(usuarioId);
        Carro nuevoCarro = carroFeignClient.save(carro);
        return nuevoCarro;
    }

    public Moto saveMoto(int usuarioId, Moto moto){
        moto.setUsuarioId(usuarioId);
        Moto nuevoMoto = motoFeignClient.save(moto);
        return nuevoMoto;
    }

    public Map<String, Object> getUsuarioAndVehiculo(int usuarioId){
        Map<String, Object> result = new HashMap<>();
        Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);

        if (usuario == null){
            result.put("Mensaje", "El usuario no existe");
        }

        result.put("Usuario", usuario);

        List<Carro> carros = carroFeignClient.getListCarro(usuarioId);
        if (carros.isEmpty()){
            result.put("Carros", "El usuario no tiene carros");
        }else{
            result.put("Carros", carros);
        }

        List<Moto> motos = motoFeignClient.getListMotos(usuarioId);
        if (motos.isEmpty()){
            result.put("Motos", "El usuario no tiene motos");
        }else{
            result.put("Motos", motos);
        }

        return result;
    }
}
