package com.example.practico1Persistencia.Repositorios;

import com.example.practico1Persistencia.Entidades.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {
}
