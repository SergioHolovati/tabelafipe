package sergioholovati.tabelafipe.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.modelmapper.ModelMapper;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.modelmapper.Conditions.isNotNull;

@ApplicationScoped
public class GenericMapper {

    @Inject
    private ModelMapper mapper;


    public  <T> List<T> converterCollection(List<?> objects, Class<T> clazz) {
        return objects.stream()
                .map(obj -> converter(obj, clazz))
                .collect(toList());
    }

    public <T> T converter(Object object, Class<T> clazz) {
        return mapper().map(object, clazz);
    }

    private  ModelMapper mapper() {
        if( mapper == null ){
            mapper = new ModelMapper();
            mapper.getConfiguration().setPropertyCondition(isNotNull());
        }

        return mapper;
    }

}