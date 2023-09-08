package com.example.practico1Persistencia.Repositorios;

import com.example.practico1Persistencia.Entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
