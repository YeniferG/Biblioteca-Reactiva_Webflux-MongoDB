package co.com.sofka.bibliotecaPublicaReactiva.router;

import co.com.sofka.bibliotecaPublicaReactiva.collection.Recurso;
import co.com.sofka.bibliotecaPublicaReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublicaReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso.UseCasePrestarRecurso;
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
@ContextConfiguration(classes = {PrestarRecursoRouter.class, UseCasePrestarRecurso.class, RecursoMapper.class})
class PrestarRecursoRouterTest {

    @MockBean
    private RepositorioRecurso repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void PrestarRecursoTest() {
        Recurso recurso = new Recurso();
        recurso.setId("xxx");
        recurso.setNombre("Fisica I");
        recurso.setDisponible(true);
        recurso.setFechaPrestamo(LocalDate.now());
        recurso.setTipo(Tipo.LIBRO);
        recurso.setAreaTematica(AreaTematica.FISICA);



        Mono<Recurso> recursoMono = Mono.just(recurso);

        when(repositorio.findById(recurso.getId())).thenReturn(recursoMono);
        when(repositorio.save(any())).thenReturn(recursoMono);


        webTestClient.put()
                .uri("/recursos/prestar/xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEqualTo("Recurso prestado exitosamente");
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).findById("xxx");

    }

    @Test
    public void PrestarRecursoNoEsPosibleTest() {
        Recurso recurso = new Recurso();
        recurso.setId("xxx");
        recurso.setNombre("Fisica I");
        recurso.setDisponible(false);
        recurso.setFechaPrestamo(LocalDate.now());
        recurso.setTipo(Tipo.LIBRO);
        recurso.setAreaTematica(AreaTematica.FISICA);

        Mono<Recurso> recursoMono = Mono.just(recurso);

        when(repositorio.findById(recurso.getId())).thenReturn(recursoMono);
        when(repositorio.save(any())).thenReturn(recursoMono);


        webTestClient.put()
                .uri("/recursos/prestar/xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse).isEqualTo("Recurso no disponible para prestamo");
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).findById("xxx");

    }

}