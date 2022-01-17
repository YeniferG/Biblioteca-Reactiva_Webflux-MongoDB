package co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso;

import co.com.sofka.bibliotecaPublicaReactiva.dto.RecursoDTO;
import co.com.sofka.bibliotecaPublicaReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublicaReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.useCase.interfaceRecurso.IGuardarRecurso;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

@Service
@Validated
public class UseCaseActualizarRecurso implements IGuardarRecurso {

    private final RepositorioRecurso repositorioRecurso;
    private final RecursoMapper recursoMapper;

    public UseCaseActualizarRecurso(RepositorioRecurso repositorioRecurso, RecursoMapper recursoMapper) {
        this.repositorioRecurso = repositorioRecurso;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<RecursoDTO> apply(RecursoDTO recursoDTO) {
        return repositorioRecurso.save(recursoMapper.mapperToRecurso(recursoDTO.getId()).apply(recursoDTO)).map(recursoMapper.mapRecursoToDto());
    }
}
