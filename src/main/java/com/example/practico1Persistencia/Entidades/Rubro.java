package com.example.practico1Persistencia.Entidades;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data //Evsita usar constructores, getters, etc.
@NoArgsConstructor //Genera constructor vacio
@AllArgsConstructor //Genera constructores sobrecargados
@Builder
public class Rubro extends BaseEntidad{

    private String denominacion;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void agregarProductos(Producto prod) {
        productos.add(prod);
    }

    public void mostrarRubro(){
        System.out.println("---RUBRO---");
        System.out.println("Denominacion: " + denominacion);
    }

}
