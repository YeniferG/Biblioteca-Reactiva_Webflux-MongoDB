package co.com.sofka.bibliotecaPublicaReactiva.router;

import co.com.sofka.bibliotecaPublicaReactiva.collection.Recurso;
import co.com.sofka.bibliotecaPublicaReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublicaReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso.UseCaseDevolverRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.utils.AreaTematica;
import co.com.sofka.bibliotecaPublicaReactiva.utils.Tipo;
import org.assertj.core.api.Assertions;
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

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DevolverRecursoRouter.class, UseCaseDevolverRecurso.class, RecursoMapper.class})
class DevolverRecursoRouterTest {

    @MockBean
    private RepositorioRecurso repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void DevolverRecursoTest() {
        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");
        recurso1.setNombre("Fisica I");
        recurso1.setDisponible(true);
        recurso1.setFechaPrestamo(LocalDate.now());
        recurso1.setTipo(Tipo.LIBRO);
        recurso1.setAreaTematica(AreaTematica.FISICA);

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findById(recurso1.getId())).thenReturn(recursoMono);
        when(repositorio.save(any())).thenReturn(recursoMono);


        webTestClient.put()
                .uri("/recursos/devolver/xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.equals("Recurso devuelto exitosamente"));
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).findById("xxx");
    }


    @Test
    public void DevolverRecursoNoEsPosibleTest() {
        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");
        recurso1.setNombre("Fisica I");
        recurso1.setDisponible(true);
        recurso1.setFechaPrestamo(LocalDate.now());
        recurso1.setTipo(Tipo.LIBRO);
        recurso1.setAreaTematica(AreaTematica.FISICA);

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findById(recurso1.getId())).thenReturn(recursoMono);


        webTestClient.put()
                .uri("/recursos/devolver/xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.equals("Recurso no se encuentra prestado"));
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).findById("xxx");
    }

}