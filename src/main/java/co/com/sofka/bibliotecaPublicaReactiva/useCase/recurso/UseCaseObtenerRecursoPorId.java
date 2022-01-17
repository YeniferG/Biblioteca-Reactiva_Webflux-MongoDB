package co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso;

import co.com.sofka.bibliotecaPublicaReactiva.dto.RecursoDTO;
import co.com.sofka.bibliotecaPublicaReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublicaReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.useCase.interfaceRecurso.IObtenerPorId;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseObtenerRecursoPorId implements IObtenerPorId {

    private final RepositorioRecurso repositorioRecurso;
    private final RecursoMapper recursoMapper;

    public UseCaseObtenerRecursoPorId(RepositorioRecurso repositorioRecurso, RecursoMapper recursoMapper) {
        this.repositorioRecurso = repositorioRecurso;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<RecursoDTO> getById(String id) {
        return repositorioRecurso.findById(id).map(recursoMapper.mapRecursoToDto());
    }
}
