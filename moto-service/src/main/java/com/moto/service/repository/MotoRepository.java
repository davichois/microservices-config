package com.moto.service.repository;

import com.moto.service.entity.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MotoRepository extends JpaRepository<Moto, Integer> {

    List<Moto> findByUsuarioId(int usuarioId);

}
