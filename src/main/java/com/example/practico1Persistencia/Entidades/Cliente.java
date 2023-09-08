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
@Builder  //Patron de diseño
//@Table(name = "persona") //Mejor que coincida con el nombre de la clase

public class Cliente extends BaseEntidad{

    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    @OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    public void agregarPedidos(Pedido ped) {
        pedidos.add(ped);
    }

    public void mostrarCliente(){
        System.out.println("---CLIENTE---");
        System.out.println("Nombre: " + nombre);
        System.out.println("Apellido: " + apellido );
        System.out.println("Telefono: " + telefono);
        System.out.println("Email: " + email);
    }

}
