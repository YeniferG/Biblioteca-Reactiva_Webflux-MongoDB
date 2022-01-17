package co.com.sofka.bibliotecaPublicaReactiva.repository;

import co.com.sofka.bibliotecaPublicaReactiva.collection.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RepositorioRecurso extends ReactiveMongoRepository<Recurso, String> {
    Flux<Recurso> findByAreaTematica(String area);
    Flux<Recurso> findByTipo(String tipo);
    Flux<Recurso> findByAreaTematicaAndTipo(String area, String tipo);
}
