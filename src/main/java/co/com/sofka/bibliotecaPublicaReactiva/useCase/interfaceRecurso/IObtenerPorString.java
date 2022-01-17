package co.com.sofka.bibliotecaPublicaReactiva.useCase.interfaceRecurso;

import co.com.sofka.bibliotecaPublicaReactiva.dto.RecursoDTO;
import reactor.core.publisher.Flux;

@FunctionalInterface
public interface IObtenerPorString {
    Flux<RecursoDTO> getByString(String string);
}
