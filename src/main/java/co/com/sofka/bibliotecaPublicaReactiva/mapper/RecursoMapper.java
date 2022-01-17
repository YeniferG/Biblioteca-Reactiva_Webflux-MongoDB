package co.com.sofka.bibliotecaPublicaReactiva.mapper;

import co.com.sofka.bibliotecaPublicaReactiva.collection.Recurso;
import co.com.sofka.bibliotecaPublicaReactiva.dto.RecursoDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RecursoMapper {

    public Function<RecursoDTO, Recurso> mapperToRecurso(String id) {
        return updateRecurso -> {
            var recurso = new Recurso();
            recurso.setId(id);
            recurso.setNombre(updateRecurso.getNombre());
            recurso.setDisponible(updateRecurso.isDisponible());
            recurso.setFechaPrestamo(updateRecurso.getFechaPrestamo());
            recurso.setTipo(updateRecurso.getTipo());
            recurso.setAreaTematica(updateRecurso.getAreaTematica());
            return recurso;
        };
    }

    public Function<Recurso, RecursoDTO> mapRecursoToDto() {
        return entity -> new RecursoDTO(
                entity.getId(),
                entity.getNombre(),
                entity.isDisponible(),
                entity.getFechaPrestamo(),
                entity.getTipo(),
                entity.getAreaTematica()
        );
    }

}
