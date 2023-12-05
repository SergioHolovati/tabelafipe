package sergioholovati.tabelafipe.domain.marca.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;

import java.util.List;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {

    public Marca buscarPorCodigo(Long codigo){
        return find("codigo",codigo).stream().findFirst().orElseThrow(NotFoundException::new);
    }

    public List<Marca> buscarTodos(){
        return findAll().stream().toList();
    }

    public void salvar(Marca marca){
        persist(marca);
    }

    public Boolean marcaCadastrada(Long codigo){
        return find("codigo",codigo).stream().findFirst().isPresent();
    }
}
