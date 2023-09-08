package com.example.practico1Persistencia.Entidades;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data //Evita usar constructores, getters, etc.
@NoArgsConstructor //Genera constructor vacio
@AllArgsConstructor //Genera constructores sobrecargados
@Builder
public class ConfiguracionGeneral extends BaseEntidad {

    private int cantidadCocineros;
    private String emailEmpresa;
    private String tokenMercadoPago;
}
