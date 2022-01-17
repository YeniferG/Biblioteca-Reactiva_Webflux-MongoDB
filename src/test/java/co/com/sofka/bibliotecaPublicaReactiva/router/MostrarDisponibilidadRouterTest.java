package co.com.sofka.bibliotecaPublicaReactiva.router;

import co.com.sofka.bibliotecaPublicaReactiva.collection.Recurso;
import co.com.sofka.bibliotecaPublicaReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublicaReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso.UseCaseDisponibilidad;
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

import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {MostrarDisponibilidadRouter.class, UseCaseDisponibilidad.class, RecursoMapper.class})
class MostrarDisponibilidadRouterTest {

    @MockBean
    private RepositorioRecurso repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void DisponibilidadTest(){
        Recurso recurso = new Recurso();
        recurso.setId("xxx");
        recurso.setNombre("Fisica I");
        recurso.setDisponible(true);
        recurso.setFechaPrestamo(LocalDate.now());
        recurso.setTipo(Tipo.LIBRO);
        recurso.setAreaTematica(AreaTematica.FISICA);

        Mono<Recurso> recursoMono = Mono.just(recurso);

        when(repositorio.findById(recurso.getId())).thenReturn(recursoMono);


        webTestClient.get()
                .uri("/recursos/disponibilidad/xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.equals("Recurso disponible"));
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).findById("xxx");
    }
}