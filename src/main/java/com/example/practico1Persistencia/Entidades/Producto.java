package com.example.practico1Persistencia.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data //Evita usar constructores, getters, etc.
@NoArgsConstructor //Genera constructor vacio
@AllArgsConstructor //Genera constructores sobrecargados
@Builder
public class Producto extends BaseEntidad {

    @Enumerated(EnumType.STRING)
    private Tipo tipo;
    private int tiempoEstimadoCocina, stockActual, stockMinimo;
    private String denominacion, unidadMedida, foto, receta;
    private double precioVenta, precioCompra;

    public enum Tipo{
        MANUFACTURADO, INSUMO
    }

    public void mostrarProducto(){
        System.out.println("---PRODUCTO---");
        System.out.println("Tipo: " + tipo);
        System.out.println("Tiempo estimado: " + tiempoEstimadoCocina + "minutos");
        System.out.println("Stock disponible: " + stockActual);
        System.out.println("Stock mínimo: " + stockMinimo);
        System.out.println("Denominación: " + denominacion);
        System.out.println("Unidad de medida: " + unidadMedida);
        System.out.println("Foto: " + foto);
        System.out.println("Receta: " + receta);
        System.out.println("Precio de venta: " + precioVenta);
        System.out.println("Precio de compra: " + precioCompra);
    }

}
