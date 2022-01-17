package co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso;

import co.com.sofka.bibliotecaPublicaReactiva.collection.Recurso;
import co.com.sofka.bibliotecaPublicaReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublicaReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.useCase.interfaceRecurso.IObtenerDisponibilidad;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
@Validated
public class UseCasePrestarRecurso implements IObtenerDisponibilidad {

    private final RepositorioRecurso repositorioRecurso;
    private final RecursoMapper recursoMapper;

    public UseCasePrestarRecurso(RepositorioRecurso repositorioRecurso, RecursoMapper recursoMapper) {
        this.repositorioRecurso = repositorioRecurso;
        this.recursoMapper = recursoMapper;
    }

    @Override
    public Mono<String> get(String id) {
        Mono<Recurso> recursoMono = repositorioRecurso.findById(id);

        return recursoMono.flatMap(recurso -> {
            if(recurso.isDisponible()){
                recurso.setDisponible(false);
                recurso.setFechaPrestamo(LocalDate.now());
                return repositorioRecurso.save(recurso).thenReturn("Recurso prestado exitosamente");
            }
            return Mono.just("Recurso no disponible para prestamo");
        });
    }
}
