package com.example.practico1Persistencia.Repositorios;

import com.example.practico1Persistencia.Entidades.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
