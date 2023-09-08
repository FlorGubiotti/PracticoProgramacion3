package com.example.practico1Persistencia.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data //Evita usar constructores, getters, etc.
@NoArgsConstructor //Genera constructor vacio
@AllArgsConstructor //Genera constructores sobrecargados
@Builder
public class Usuario extends BaseEntidad{

    private String nombre;
    private String password;
    private String rol;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarPedido(Pedido ped) {
        pedidos.add(ped);
    }

    public void mostrarUsuario(){
        System.out.println("---USUARIO---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Password: " + password);
        System.out.println("Rol: " + rol);
    }
}
