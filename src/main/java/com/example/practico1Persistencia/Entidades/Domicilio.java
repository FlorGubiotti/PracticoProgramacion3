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
public class Domicilio extends BaseEntidad{

    private String calle;
    private String numero;
    private String localidad;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "domicilio_id")
    @Builder.Default
    private List<Pedido> pedidos = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente clientes;

    public void agregarPedidos(Pedido ped) {
        pedidos.add(ped);
    }

    public void mostrarDomicilio(){
        System.out.println("---DOMICILIO---");
        System.out.println("Calle: " + calle);
        System.out.println("Número: " + numero);
        System.out.println("Localidad: " + localidad);
    }
}
