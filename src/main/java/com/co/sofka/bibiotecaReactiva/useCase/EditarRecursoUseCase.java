package com.co.sofka.bibiotecaReactiva.useCase;

import com.co.sofka.bibiotecaReactiva.dto.RecursoDTO;
import com.co.sofka.bibiotecaReactiva.mapper.RecursoMapper;
import com.co.sofka.bibiotecaReactiva.model.Recurso;
import com.co.sofka.bibiotecaReactiva.repository.IRecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class EditarRecursoUseCase implements SaveRecurso{

    private final IRecursoRepository iRecursoRepository;
    private final RecursoMapper recursoMapper;

    public EditarRecursoUseCase(IRecursoRepository iRecursoRepository, RecursoMapper recursoMapper) {
        this.iRecursoRepository = iRecursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<String> apply(RecursoDTO recursoDTO) {
        Objects.requireNonNull(recursoDTO.getId(), "El id del recurso es requerido");
        return iRecursoRepository
                .save(recursoMapper.mapperToRecurso(recursoDTO.getId()).apply(recursoDTO))
                .map(Recurso::getId);

    }
}
