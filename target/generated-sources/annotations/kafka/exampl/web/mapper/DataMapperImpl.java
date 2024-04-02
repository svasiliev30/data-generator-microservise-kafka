package kafka.exampl.web.mapper;

import javax.annotation.processing.Generated;
import kafka.exampl.model.Data;
import kafka.exampl.web.dto.DataDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-02T21:09:10+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class DataMapperImpl implements DataMapper {

    @Override
    public Data toEntity(DataDto dto) {
        if ( dto == null ) {
            return null;
        }

        Data data = new Data();

        data.setSensorId( dto.getSensorId() );
        data.setTimestamp( dto.getTimestamp() );
        data.setMeasurement( dto.getMeasurement() );
        data.setMeasurementType( dto.getMeasurementType() );

        return data;
    }

    @Override
    public DataDto toDto(Data entity) {
        if ( entity == null ) {
            return null;
        }

        DataDto dataDto = new DataDto();

        dataDto.setSensorId( entity.getSensorId() );
        dataDto.setTimestamp( entity.getTimestamp() );
        dataDto.setMeasurement( entity.getMeasurement() );
        dataDto.setMeasurementType( entity.getMeasurementType() );

        return dataDto;
    }
}
