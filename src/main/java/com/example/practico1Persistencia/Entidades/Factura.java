package com.example.practico1Persistencia.Entidades;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //Evita usar constructores, getters, etc.
@NoArgsConstructor //Genera constructor vacio
@AllArgsConstructor //Genera constructores sobrecargados
@Builder
public class Factura extends BaseEntidad{

    private String fecha;
    private int numero;
    private double descuento;
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;
    private int total;

    public enum FormaPago{
        EFECTIVO, DEBITO, CREDITO, MPAGO
    }

    public int Total(Pedido p){
        if(descuento>0 && descuento<=100){
            double descuentoAplicado = p.getTotal() * (descuento/100);
            double suma = p.getTotal() - descuentoAplicado;
            total = (int)Math.round(suma);
            return total;
        }else{
            total= (int)Math.round(p.getTotal());
            return total;
        }
    }

    public void mostrarFactura(){
        System.out.println("---FACTURA---");
        System.out.println("Fecha: " + fecha);
        System.out.println("Número: " + numero);
        System.out.println("Descuento: " + descuento + "%");
        System.out.println("Forma de Pago: " + formaPago);
        System.out.println("Total: $" + total);
    }
}
