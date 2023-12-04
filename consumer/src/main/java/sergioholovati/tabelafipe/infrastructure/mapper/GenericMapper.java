package sergioholovati.tabelafipe.infrastructure.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.modelmapper.Conditions.isNotNull;

@Component
public class GenericMapper {

    @Autowired
    private ModelMapper mapper;

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