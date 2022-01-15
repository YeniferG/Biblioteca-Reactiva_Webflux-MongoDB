package co.com.sofka.bibliotecaPublicaReactiva.mapper;

import co.com.sofka.bibliotecaPublicaReactiva.collection.Recurso;
import co.com.sofka.bibliotecaPublicaReactiva.dto.RecursoDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    public Recurso fromDTO(RecursoDTO dto) {
        Recurso recurso = new Recurso();
        recurso.setId(dto.getId());
        recurso.setNombre(dto.getNombre());
        recurso.setDisponible(dto.isDisponible());
        recurso.setFechaPrestamo(dto.getFechaPrestamo());
        recurso.setTipo(dto.getTipo());
        recurso.setAreaTematica(dto.getAreaTematica());
        return recurso;
    }

    public RecursoDTO fromCollection(Recurso collection) {
        RecursoDTO recursoDTO = new RecursoDTO();
        recursoDTO.setId(collection.getId());
        recursoDTO.setNombre(collection.getNombre());
        recursoDTO.setDisponible(collection.isDisponible());
        recursoDTO.setFechaPrestamo(collection.getFechaPrestamo());
        recursoDTO.setTipo(collection.getTipo());
        recursoDTO.setAreaTematica(collection.getAreaTematica());
        return recursoDTO;
    }

    public List<RecursoDTO> fromCollectionList(List<Recurso> collection) {
        if (collection == null) {
            return null;
        }
        List<RecursoDTO> list = new ArrayList(collection.size());
        Iterator listTracks = collection.iterator();

        while(listTracks.hasNext()) {
            Recurso recurso = (Recurso)listTracks.next();
            list.add(fromCollection(recurso));
        }
        return list;
    }

}
