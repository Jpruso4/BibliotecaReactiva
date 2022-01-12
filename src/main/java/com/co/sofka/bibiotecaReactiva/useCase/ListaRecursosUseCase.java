package com.co.sofka.bibiotecaReactiva.useCase;

import com.co.sofka.bibiotecaReactiva.dto.RecursoDTO;
import com.co.sofka.bibiotecaReactiva.mapper.RecursoMapper;
import com.co.sofka.bibiotecaReactiva.repository.IRecursoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ListaRecursosUseCase implements Supplier<Flux<RecursoDTO>> {

    private final IRecursoRepository iRecursoRepository;
    private final RecursoMapper recursoMapper;

    public ListaRecursosUseCase(IRecursoRepository iRecursoRepository, RecursoMapper recursoMapper) {
        this.iRecursoRepository = iRecursoRepository;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Flux<RecursoDTO> get() {
        return iRecursoRepository.findAll()
                .map(recursoMapper.mapperToDTO());
    }
}
