package co.com.sofka.bibliotecaPublicaReactiva.router;

import co.com.sofka.bibliotecaPublicaReactiva.useCase.recurso.UseCaseDisponibilidad;
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
public class MostrarDisponibilidadRouter {

    @Bean
    public RouterFunction<ServerResponse> mostrarDisponibilidad(UseCaseDisponibilidad useCaseDisponibilidad) {
        return route(
                GET("/recursos/disponibilidad/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCaseDisponibilidad.get(request.pathVariable("id")), String.class))
                        .onErrorResume((Error) -> ServerResponse.badRequest().build())
        );
    }

}
