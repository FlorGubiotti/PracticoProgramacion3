package com.example.practico1Persistencia.Repositorios;

import com.example.practico1Persistencia.Entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
