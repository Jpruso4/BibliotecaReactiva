package com.co.sofka.bibiotecaReactiva.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recurso {

    @Id
    private String id;
    private String nombre;
    private String tipo;
    private String tematica;
    private LocalDate fechaDePrestamo;
    private boolean estado;

}
