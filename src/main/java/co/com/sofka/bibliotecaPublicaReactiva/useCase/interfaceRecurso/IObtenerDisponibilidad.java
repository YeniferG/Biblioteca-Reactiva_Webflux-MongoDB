package co.com.sofka.bibliotecaPublicaReactiva.useCase.interfaceRecurso;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface IObtenerDisponibilidad {
    Mono<String> get(String id);
}
