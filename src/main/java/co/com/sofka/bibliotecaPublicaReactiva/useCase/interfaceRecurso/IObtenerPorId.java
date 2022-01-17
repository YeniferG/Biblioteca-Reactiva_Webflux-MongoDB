package co.com.sofka.bibliotecaPublicaReactiva.useCase.interfaceRecurso;

import co.com.sofka.bibliotecaPublicaReactiva.dto.RecursoDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface IObtenerPorId {
    Mono<RecursoDTO> getById(String id);
}
