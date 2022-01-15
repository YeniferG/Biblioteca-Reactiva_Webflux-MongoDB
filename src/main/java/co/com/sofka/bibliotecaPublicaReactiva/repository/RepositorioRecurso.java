package co.com.sofka.bibliotecaPublicaReactiva.repository;


import co.com.sofka.bibliotecaPublicaReactiva.collection.Recurso;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioRecurso extends ReactiveMongoRepository<Recurso, String> {
}
