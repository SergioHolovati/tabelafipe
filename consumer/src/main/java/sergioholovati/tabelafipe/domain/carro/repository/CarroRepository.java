package sergioholovati.tabelafipe.domain.carro.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import sergioholovati.tabelafipe.domain.carro.entity.Carro;
import sergioholovati.tabelafipe.domain.fipe.FipeModeloDTO;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;

import java.util.List;


@ApplicationScoped
public class CarroRepository  implements PanacheRepository<Carro> {

    public List<Carro> buscarPorMarca(Marca marca){
        return list("marca",marca);
    }

    public void salvar(FipeModeloDTO modelo, Marca marca, Long codigo){
         persist(Carro.builder()
                 .nome(modelo.getNome())
                 .codigo(codigo)
                 .marca(marca)
                 .build());
    }

    public Boolean carroExiste(Long carro){
        return find("codigo",carro).stream().findFirst().isPresent();
    }
}
