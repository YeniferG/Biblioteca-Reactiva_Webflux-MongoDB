package co.com.sofka.bibliotecaPublicaReactiva.router;

import co.com.sofka.bibliotecaPublicaReactiva.collection.Recurso;
import co.com.sofka.bibliotecaPublicaReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublicaReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso.UseCaseEliminarRecurso;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {EliminarRecursoRouter.class, UseCaseEliminarRecurso.class, RecursoMapper.class})
class EliminarRecursoRouterTest {

    @MockBean
    RepositorioRecurso repositorio;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void eliminarRecursoTest(){
        Recurso recurso = new Recurso();
        recurso.setId("xxx");

        when(repositorio.deleteById("xxx")).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/recursos/eliminar/xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        Mockito.verify(repositorio,Mockito.times(1)).deleteById("xxx");
    }
}