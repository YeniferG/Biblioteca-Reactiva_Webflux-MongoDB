package co.com.sofka.bibliotecaPublicaReactiva.router;

import co.com.sofka.bibliotecaPublicaReactiva.collection.Recurso;
import co.com.sofka.bibliotecaPublicaReactiva.dto.RecursoDTO;
import co.com.sofka.bibliotecaPublicaReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublicaReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso.UseCaseBuscarPorTipo;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= {FiltrarPorTipoRouter.class, UseCaseBuscarPorTipo.class, RecursoMapper.class})
class FiltrarPorTipoRouterTest {

    @MockBean
    RepositorioRecurso repositorio;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void filtrarPorTipoTest(){
        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");
        recurso1.setNombre("Fisica Experimental");
        recurso1.setDisponible(true);
        recurso1.setFechaPrestamo(LocalDate.now());
        recurso1.setTipo(Tipo.DOCUMENTAL);
        recurso1.setAreaTematica(AreaTematica.FISICA);

        Recurso recurso2 = new Recurso();
        recurso2.setId("yyy");
        recurso2.setNombre("Libro");
        recurso2.setDisponible(true);
        recurso2.setFechaPrestamo(LocalDate.now());
        recurso2.setTipo(Tipo.LIBRO);
        recurso2.setAreaTematica(AreaTematica.MATEMATICAS);

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findByTipo(recurso1.getTipo().toString())).thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/recursos/filtrarTipo/DOCUMENTAL")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getTipo()).isEqualTo(recurso1.getTipo());
                            Assertions.assertThat(userResponse.get(1).getTipo()).isEqualTo(recurso2.getTipo());
                        }
                );

        Mockito.verify(repositorio,Mockito.times(1)).findByTipo("DOCUMENTAL");
    }

}