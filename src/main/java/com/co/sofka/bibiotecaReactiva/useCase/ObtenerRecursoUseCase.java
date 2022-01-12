package com.co.sofka.bibiotecaReactiva.useCase;

import com.co.sofka.bibiotecaReactiva.dto.RecursoDTO;
import com.co.sofka.bibiotecaReactiva.mapper.RecursoMapper;
import com.co.sofka.bibiotecaReactiva.repository.IRecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class ObtenerRecursoUseCase {

    private final IRecursoRepository recursoRepository;
    private final RecursoMapper recursoMapper;

    public ObtenerRecursoUseCase(IRecursoRepository recursoRepository, RecursoMapper recursoMapper) {
        this.recursoRepository = recursoRepository;
        this.recursoMapper = recursoMapper;
    }

    public Mono<RecursoDTO> obtenerRecurso(String id) {
        Objects.requireNonNull(id, "El id del recurso es requerido");
        return recursoRepository.findById(id)
                .map(recursoMapper.mapperToDTO());
    }
}
