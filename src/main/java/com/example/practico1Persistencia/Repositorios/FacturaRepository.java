package com.example.practico1Persistencia.Repositorios;

import com.example.practico1Persistencia.Entidades.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
}
