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
public class Pedido extends BaseEntidad{

    private String fecha;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private String horaEstimada;
    @Enumerated(EnumType.STRING)
    private TipoEnvio tipoEnvio;
    private double total;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "detallePedido_id")
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "factura_id")
    private Factura factura;

    public enum Estado{
        INICIADO, PREPARACION, ENTREGADO
    }

    public enum TipoEnvio{
        DELIVERY, RETIRA
    }

    public void agregarDetallePedido(DetallePedido detpedido) {
        detallePedidos.add(detpedido);
    }

    public double Total(){
        double suma=0;
        for(DetallePedido dt: detallePedidos){
            suma += (dt.getSubtotal() * dt.getCantidad());
        }
        return suma;
    }

    public void mostrarPedido(){
        System.out.println("---PEDIDO---");
        System.out.println("Fecha: " + fecha);
        System.out.println("Estado: " + estado);
        System.out.println("Hora estimada: " + horaEstimada);
        System.out.println("Tipo de envío: " + tipoEnvio);
        System.out.println("Total: $" + total);
    }

}
