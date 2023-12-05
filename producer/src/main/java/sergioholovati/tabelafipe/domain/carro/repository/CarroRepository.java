package sergioholovati.tabelafipe.domain.carro.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import sergioholovati.tabelafipe.controller.queryparams.QueryParams;
import sergioholovati.tabelafipe.domain.carro.entity.Carro;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;

import java.util.List;

@ApplicationScoped
public class CarroRepository implements PanacheRepository<Carro> {

    public Carro buscarPorId(Long id){
        return findById(id);
    }

    public Carro buscarPorCodigo(Long codigo){
        return find("codigo",codigo).stream().findFirst().orElseThrow(NotFoundException::new);
    }

   public List<Carro> buscarPorMarca(Marca marca, QueryParams params){
       return find("marca",marca).page(params.getIndex(),params.getSize()).stream().toList();
    }

   public List<Carro> buscarTodos(QueryParams params){
       return findAll().page(params.getIndex(),params.getSize()).stream().toList();
   }

   public void salvar(Carro carro){
        persist(carro);
   }

}
