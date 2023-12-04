package sergioholovati.tabelafipe.infrastructure.kafka;

import io.quarkus.kafka.client.serialization.ObjectMapperDeserializer;
import sergioholovati.tabelafipe.client.domain.MarcaExtension;

public class MarcaExtensionDeserializer extends ObjectMapperDeserializer<MarcaExtension> {

    public MarcaExtensionDeserializer() {
        super(MarcaExtension.class);
    }
}
