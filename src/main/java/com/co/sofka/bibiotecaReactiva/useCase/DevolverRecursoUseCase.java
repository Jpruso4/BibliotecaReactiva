package com.co.sofka.bibiotecaReactiva.useCase;

import com.co.sofka.bibiotecaReactiva.mapper.RecursoMapper;
import com.co.sofka.bibiotecaReactiva.model.Recurso;
import com.co.sofka.bibiotecaReactiva.repository.IRecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@Validated
public class DevolverRecursoUseCase {

    private final IRecursoRepository recursoRepository;

    public DevolverRecursoUseCase(IRecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
    }

    public Mono<String> devolverRecurso(String id){
        Mono<Recurso> recurso = recursoRepository.findById(id);
        return recurso.flatMap(recursoModified ->{
            if (!recursoModified.isEstado()){
                recursoModified.setEstado(true);
                recursoModified.setFechaDePrestamo(null);
                return recursoRepository.save(recursoModified).thenReturn("El reccurso se ha devuelto con exito");
            }
            return Mono.just("El recurso no se puede devolver ya que no se ha prestado");
        });
    }
}
