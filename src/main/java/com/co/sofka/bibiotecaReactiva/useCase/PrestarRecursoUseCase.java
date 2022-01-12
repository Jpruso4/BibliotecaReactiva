package com.co.sofka.bibiotecaReactiva.useCase;

import com.co.sofka.bibiotecaReactiva.dto.RecursoDTO;
import com.co.sofka.bibiotecaReactiva.mapper.RecursoMapper;
import com.co.sofka.bibiotecaReactiva.model.Recurso;
import com.co.sofka.bibiotecaReactiva.repository.IRecursoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.time.LocalDate;

@Service
@Valid
public class PrestarRecursoUseCase {

    private final IRecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public PrestarRecursoUseCase(IRecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    public Mono<String> prestarRecurso(String id){
        Mono<Recurso> recurso = recursoRepository.findById(id);

        return recurso.flatMap(recursoModified ->{
            if (recursoModified.isEstado()){
                recursoModified.setEstado(false);
                recursoModified.setFechaDePrestamo(LocalDate.now());
                return recursoRepository.save(recursoModified).thenReturn("El prestamo se ha hecho correctamente " + recursoModified.getFechaDePrestamo());
            }
            return Mono.just("El recurso no se encuentra disponible");
        });
    }
}
