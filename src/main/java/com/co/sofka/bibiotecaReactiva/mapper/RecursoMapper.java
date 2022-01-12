package com.co.sofka.bibiotecaReactiva.mapper;

import com.co.sofka.bibiotecaReactiva.dto.RecursoDTO;
import com.co.sofka.bibiotecaReactiva.model.Recurso;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RecursoMapper {

    public Function<RecursoDTO, Recurso> mapperToRecurso(String id){
        return updateRecurso ->{
            var recurso = new Recurso();
            recurso.setId(id);
            recurso.setNombre(updateRecurso.getNombre());
            recurso.setTipo(updateRecurso.getTipo());
            recurso.setTematica(updateRecurso.getTematica());
            recurso.setFechaDePrestamo(updateRecurso.getFechaDePrestamo());
            recurso.setEstado(!recurso.isEstado());
            return recurso;
        };
    }

    public Function<Recurso, RecursoDTO> mapperToDTO(){
        return entity -> new RecursoDTO(
          entity.getId(),
          entity.getNombre(),
          entity.getTipo(),
          entity.getTematica(),
          entity.getFechaDePrestamo(),
          entity.isEstado()
        );
    }

}
