package com.example.practico1Persistencia.Repositorios;

import com.example.practico1Persistencia.Entidades.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomicilioRepository extends JpaRepository<Domicilio, Long> {
}
