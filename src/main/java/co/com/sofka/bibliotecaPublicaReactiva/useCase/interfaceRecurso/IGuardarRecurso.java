package co.com.sofka.bibliotecaPublicaReactiva.useCase.interfaceRecurso;

import co.com.sofka.bibliotecaPublicaReactiva.dto.RecursoDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface IGuardarRecurso {
    Mono<RecursoDTO> apply(RecursoDTO recursoDTO);
}
