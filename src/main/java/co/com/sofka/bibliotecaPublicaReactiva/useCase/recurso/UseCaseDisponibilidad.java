package co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso;

import co.com.sofka.bibliotecaPublicaReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublicaReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.useCase.interfaceRecurso.IObtenerDisponibilidad;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseDisponibilidad implements IObtenerDisponibilidad {

    private final RepositorioRecurso repositorioRecurso;
    private final RecursoMapper recursoMapper;

    public UseCaseDisponibilidad(RepositorioRecurso repositorioRecurso, RecursoMapper recursoMapper) {
        this.repositorioRecurso = repositorioRecurso;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<String> get(String id) {
        return repositorioRecurso.findById(id).map(recurso -> recurso.isDisponible() ? "Recurso disponible" : "Recurso no disponible, fue prestado el: " + recurso.getFechaPrestamo());
    }
}
