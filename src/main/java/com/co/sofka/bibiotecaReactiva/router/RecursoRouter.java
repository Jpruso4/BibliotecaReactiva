package com.co.sofka.bibiotecaReactiva.router;

import com.co.sofka.bibiotecaReactiva.dto.RecursoDTO;
import com.co.sofka.bibiotecaReactiva.useCase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RecursoRouter {

    @Bean
    public RouterFunction<ServerResponse> obtenerListaRecursos(ListaRecursosUseCase listaRecursosUseCase) {
        return route(GET("/recursos"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(listaRecursosUseCase.get(), RecursoDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerRecurso(ObtenerRecursoUseCase obtenerRecursoUseCase) {
        return route(
                GET("/recursos/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(obtenerRecursoUseCase.obtenerRecurso(request.pathVariable("id")), RecursoDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> crearRecurso(CrearRecursoUseCase crearRecursoUseCase){

        Function<RecursoDTO, Mono<ServerResponse>> executor = recursoDTO ->  crearRecursoUseCase.apply(recursoDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .bodyValue(result));

        return route(
                POST("/recursos/crear").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> eliminarRecurso(EliminarRecursoUseCase eliminarRecursoUseCase) {
        return route(
                DELETE("/recursos/eliminar/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(eliminarRecursoUseCase.eliminarRecurso(request.pathVariable("id")), String.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> editarRecurso(EditarRecursoUseCase editarRecursoUseCase){
        return route(PUT("/recursos/modificar").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(RecursoDTO.class)
                        .flatMap(userDTO -> editarRecursoUseCase.apply(userDTO)
                                .flatMap(result -> ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                        )
        );
    }

    @Bean
    public RouterFunction<ServerResponse> prestarRecurso(PrestarRecursoUseCase prestarRecursoUseCase) {
        return route(
                PUT("/recursos/prestarRecurso/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(prestarRecursoUseCase.prestarRecurso(request.pathVariable("id")), String.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerDisponibilidad(DisponibilidadRecursoUseCase disponibilidadRecursoUseCase) {
        return route(
                GET("/recursos/disponibilidad/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(disponibilidadRecursoUseCase.obtenerDisponibilidad(request.pathVariable("id")), String.class))
                        .onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }

    @Bean
    public RouterFunction<ServerResponse> devolverRecurso(DevolverRecursoUseCase devolverRecursoUseCase) {
        return route(
                PUT("/recursos/devolverRecurso/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(devolverRecursoUseCase.devolverRecurso(request.pathVariable("id")), String.class))
        );
    }

}