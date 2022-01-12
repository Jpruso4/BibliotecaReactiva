package com.co.sofka.bibiotecaReactiva.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecursoDTO {

    private String id;
    private String nombre;
    private String tipo;
    private String tematica;
    private LocalDate fechaDePrestamo;
    private boolean estado;

}
