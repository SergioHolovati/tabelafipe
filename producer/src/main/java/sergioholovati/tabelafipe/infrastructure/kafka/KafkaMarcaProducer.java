package sergioholovati.tabelafipe.infrastructure.kafka;


import sergioholovati.tabelafipe.client.domain.MarcaExtension;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@ApplicationScoped
public class KafkaMarcaProducer {

    @Inject
    @Channel("sincronizar-marcas-fipe")
    Emitter<MarcaExtension> emitter;

    public void sendPost(MarcaExtension post) {
        emitter.send(post);
    }

}