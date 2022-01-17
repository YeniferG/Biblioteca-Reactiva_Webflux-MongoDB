package co.com.sofka.bibliotecaPublicaReactiva.router;

import co.com.sofka.bibliotecaPublicaReactiva.dto.RecursoDTO;
import co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso.UseCaseObtenerRecursos;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ObtenerRecursosRouter {

    @Bean
    public RouterFunction<ServerResponse> obtenerRecursos(UseCaseObtenerRecursos useCaseObtenerRecursos) {
        return route(
                GET("/recursos").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseObtenerRecursos.get(), RecursoDTO.class))
        );
    }

}
