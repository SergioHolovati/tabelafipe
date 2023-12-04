package sergioholovati.tabelafipe.infrastructure.kafka;

import sergioholovati.tabelafipe.client.domain.MarcaExtension;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/api/kafka")
@Produces(MediaType.APPLICATION_JSON)
public class KafkaResource{
    @Inject
    KafkaMarcaProducer producer;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void send(MarcaExtension post) {
        producer.sendPost(post);
    }
}
