package co.com.sofka.bibliotecaPublicaReactiva.router;

import co.com.sofka.bibliotecaPublicaReactiva.collection.Recurso;
import co.com.sofka.bibliotecaPublicaReactiva.dto.RecursoDTO;
import co.com.sofka.bibliotecaPublicaReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublicaReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso.UseCaseCrearRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.utils.AreaTematica;
import co.com.sofka.bibliotecaPublicaReactiva.utils.Tipo;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {CrearRecursoRouter.class, UseCaseCrearRecurso.class, RecursoMapper.class})
class CrearRecursoRouterTest {

    @MockBean
    private RepositorioRecurso repositorio;

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testCrearRecurso() {

        Recurso recurso = new Recurso();
        recurso.setId("xxx");
        recurso.setNombre("Fisica I");
        recurso.setDisponible(true);
        recurso.setFechaPrestamo(LocalDate.now());
        recurso.setTipo(Tipo.LIBRO);
        recurso.setAreaTematica(AreaTematica.FISICA);

        RecursoDTO recursoDTO = new RecursoDTO(
                recurso.getId(),
                recurso.getNombre(),
                recurso.isDisponible(),
                recurso.getFechaPrestamo(),
                recurso.getTipo(),
                recurso.getAreaTematica());

        Mono<Recurso> recursoMono = Mono.just(recurso);

        when(repositorio.save(any())).thenReturn(recursoMono);

        webTestClient.post()
                .uri("/recursos/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(recursoDTO), RecursoDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.getId()).isEqualTo(recurso.getId());
                            Assertions.assertThat(userResponse.getNombre()).isEqualTo(recurso.getNombre());
                            Assertions.assertThat(userResponse.isDisponible()).isEqualTo(recurso.isDisponible());
                            Assertions.assertThat(userResponse.getTipo()).isEqualTo(recurso.getTipo());
                            Assertions.assertThat(userResponse.getAreaTematica()).isEqualTo(recurso.getAreaTematica());
                        }
                );
        Mockito.verify(repositorio,Mockito.times(1)).save(any());
    }


}