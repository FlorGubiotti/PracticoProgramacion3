package com.example.practico1Persistencia.Repositorios;

import com.example.practico1Persistencia.Entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
