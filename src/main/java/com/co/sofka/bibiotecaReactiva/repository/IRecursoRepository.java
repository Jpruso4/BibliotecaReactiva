package com.co.sofka.bibiotecaReactiva.repository;

import com.co.sofka.bibiotecaReactiva.model.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IRecursoRepository extends ReactiveMongoRepository<Recurso, String>{
}
