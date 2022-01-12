package com.co.sofka.bibiotecaReactiva.useCase;

import com.co.sofka.bibiotecaReactiva.dto.RecursoDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@FunctionalInterface
public interface SaveRecurso {

    Mono<String> apply(@Valid RecursoDTO recursoDTO);
}
