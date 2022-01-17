package co.com.sofka.bibliotecaPublicaReactiva.router;

import co.com.sofka.bibliotecaPublicaReactiva.collection.Recurso;
import co.com.sofka.bibliotecaPublicaReactiva.dto.RecursoDTO;
import co.com.sofka.bibliotecaPublicaReactiva.mapper.RecursoMapper;
import co.com.sofka.bibliotecaPublicaReactiva.repository.RepositorioRecurso;
import co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso.UseCaseObtenerRecursos;
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

import java.time.LocalDate;

import static org.mockito.Mockito.when;

@WebFluxTest
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {ObtenerRecursosRouter.class, UseCaseObtenerRecursos.class, RecursoMapper.class})
class ObtenerRecursosRouterTest {

    @MockBean
    private RepositorioRecurso repositorio;

    @Autowired
    private WebTestClient webTestClient;


    @Test
    public void testObtenerRecursos() {

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

        when(repositorio.findAll()).thenReturn(Flux.just(recurso1, recurso2));

        webTestClient.get()
                .uri("/recursos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(RecursoDTO.class)
                .value(userResponse -> {
                            Assertions.assertThat(userResponse.get(0).getNombre()).isEqualTo(recurso1.getNombre());
                            Assertions.assertThat(userResponse.get(1).getNombre()).isEqualTo(recurso2.getNombre());
                        }
                );

        Mockito.verify(repositorio,Mockito.times(1)).findAll();
    }


}