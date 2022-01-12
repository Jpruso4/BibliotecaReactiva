package com.co.sofka.bibiotecaReactiva.useCase;

import com.co.sofka.bibiotecaReactiva.dto.RecursoDTO;
import com.co.sofka.bibiotecaReactiva.mapper.RecursoMapper;
import com.co.sofka.bibiotecaReactiva.model.Recurso;
import com.co.sofka.bibiotecaReactiva.repository.IRecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class CrearRecursoUseCase implements SaveRecurso {

    private final RecursoMapper recursoMapper;
    private final IRecursoRepository iRecursoRepository;

    public CrearRecursoUseCase(RecursoMapper recursoMapper, IRecursoRepository iRecursoRepository) {
        this.recursoMapper = recursoMapper;
        this.iRecursoRepository = iRecursoRepository;
    }

    @Override
    public Mono<String> apply(RecursoDTO recursoDTO) {
         return iRecursoRepository
                 .save(recursoMapper.mapperToRecurso(null).apply(recursoDTO))
                .map(Recurso::getId);
    }
}
