package com.co.sofka.bibiotecaReactiva.useCase;

import com.co.sofka.bibiotecaReactiva.mapper.RecursoMapper;
import com.co.sofka.bibiotecaReactiva.repository.IRecursoRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Valid
public class DisponibilidadRecursoUseCase {

    private final IRecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public DisponibilidadRecursoUseCase(IRecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    public Mono<String> obtenerDisponibilidad(String id){
        return recursoRepository.findById(id).map(recurso ->
                recurso.isEstado() ? "El recurso se encuentra disponible"
                        : "El reccurso no se encuentra disponible en el momento y la fecha del prestamo del ejemplar fue: " + recurso.getFechaDePrestamo()
        );
    }
}
