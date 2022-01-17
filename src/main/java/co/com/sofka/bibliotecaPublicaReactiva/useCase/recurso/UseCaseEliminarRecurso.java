package co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso;

import co.com.sofka.bibliotecaPublicaReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublicaReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.useCase.interfaceRecurso.IEliminarRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseEliminarRecurso implements IEliminarRecurso {

    private final RepositorioRecurso repositorioRecurso;
    private final RecursoMapper recursoMapper;

    public UseCaseEliminarRecurso(RepositorioRecurso repositorioRecurso, RecursoMapper recursoMapper) {
        this.repositorioRecurso = repositorioRecurso;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repositorioRecurso.deleteById(id);
    }
}
