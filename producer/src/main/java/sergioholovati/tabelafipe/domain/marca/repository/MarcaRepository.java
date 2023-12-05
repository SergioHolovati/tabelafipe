package sergioholovati.tabelafipe.domain.marca.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import sergioholovati.tabelafipe.controller.queryparams.QueryParams;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;

import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {

    public Marca buscarPorCodigo(Long codigo){
        return find("codigo",codigo).stream().findFirst().orElseThrow(NotFoundException::new);
    }

    public List<Marca> buscarTodos(QueryParams params){
        return findAll().page(params.getIndex(),params.getSize()).stream().toList();
    }

}
