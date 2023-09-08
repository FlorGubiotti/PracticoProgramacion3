package com.example.practico1Persistencia.Entidades;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@MappedSuperclass //no crea tabla, se toma como superclase
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntidad implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
