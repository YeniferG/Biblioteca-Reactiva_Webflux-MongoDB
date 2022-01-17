package co.com.sofka.bibliotecaPublicaReactiva.router;

import co.com.sofka.bibliotecaPublicaReactiva.collection.Recurso;
import co.com.sofka.bibliotecaPublicaReactiva.dto.RecursoDTO;
import co.com.sofka.bibliotecaPublicaReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublicaReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso.UseCaseObtenerRecursoPorId;
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
@ContextConfiguration(classes= {ObtenerRecursoPorIdRouter.class, UseCaseObtenerRecursoPorId.class, RecursoMapper.class})
class ObtenerRecursoPorIdRouterTest {

    @MockBean
    RepositorioRecurso repositorio;

    @Autowired
    WebTestClient webTestClient;

    @Test
    public void obtenerPorIdTest(){
        Recurso recurso1 = new Recurso();
        recurso1.setId("xxx");
        recurso1.setNombre("Manualidades");
        recurso1.setDisponible(true);
        recurso1.setFechaPrestamo(LocalDate.now());
        recurso1.setTipo(Tipo.DOCUMENTAL);
        recurso1.setAreaTematica(AreaTematica.ARTES);

        Mono<Recurso> recursoMono = Mono.just(recurso1);

        when(repositorio.findById(recurso1.getId())).thenReturn(recursoMono);

        webTestClient.get()
                .uri("/recursos/buscar/xxx")
                .exchange()
                .expectStatus().isOk()
                .expectBody(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.getId()).isEqualTo(recurso1.getId());
                            Assertions.assertThat(userResponse.getNombre()).isEqualTo(recurso1.getNombre());
                            Assertions.assertThat(userResponse.isDisponible()).isEqualTo(recurso1.isDisponible());
                            Assertions.assertThat(userResponse.getTipo()).isEqualTo(recurso1.getTipo());
                            Assertions.assertThat(userResponse.getAreaTematica()).isEqualTo(recurso1.getAreaTematica());

                        }
                );

        Mockito.verify(repositorio,Mockito.times(1)).findById("xxx");
    }

}