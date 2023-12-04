package sergioholovati.tabelafipe.domain.marca.service;

import sergioholovati.tabelafipe.client.domain.MarcaExtension;
import sergioholovati.tabelafipe.domain.marca.entity.Marca;

public interface MarcaService {

    Iterable<Marca> buscarMarcas();

    void sincronizarMarcas(MarcaExtension marcaExtension);

}
