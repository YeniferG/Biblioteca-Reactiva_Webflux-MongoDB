package co.com.sofka.bibliotecaPublicaReactiva.useCase.interfaceRecurso;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface IEliminarRecurso {
    Mono<Void> deleteById(String id);
}
