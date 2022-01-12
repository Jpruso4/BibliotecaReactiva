package com.co.sofka.bibiotecaReactiva.useCase;

import com.co.sofka.bibiotecaReactiva.repository.IRecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class EliminarRecursoUseCase {

    private final IRecursoRepository iRecursoRepository;

    public EliminarRecursoUseCase(IRecursoRepository iRecursoRepository) {
        this.iRecursoRepository = iRecursoRepository;
    }

    public Mono<String> eliminarRecurso(String id) {
        Objects.requireNonNull(id, "Id is required");
        return iRecursoRepository.deleteById(id).thenReturn("El recurso fue eliminado con exito");
    }
}
