package kafka.exampl.web.mapper;

import java.util.Arrays;
import javax.annotation.processing.Generated;
import kafka.exampl.model.Data;
import kafka.exampl.model.test.DataTestOptions;
import kafka.exampl.web.dto.DataTestOptionsDto;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-20T19:33:54+0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.1 (Oracle Corporation)"
)
@Component
public class DataTestOptionsMapperImpl implements DataTestOptionsMapper {

    @Override
    public DataTestOptions toEntity(DataTestOptionsDto dto) {
        if ( dto == null ) {
            return null;
        }

        DataTestOptions dataTestOptions = new DataTestOptions();

        dataTestOptions.setDelayInSeconds( dto.getDelayInSeconds() );
        Data.MeasurementType[] measurementTypes = dto.getMeasurementTypes();
        if ( measurementTypes != null ) {
            dataTestOptions.setMeasurementTypes( Arrays.copyOf( measurementTypes, measurementTypes.length ) );
        }

        return dataTestOptions;
    }

    @Override
    public DataTestOptionsDto toDto(DataTestOptions entity) {
        if ( entity == null ) {
            return null;
        }

        DataTestOptionsDto dataTestOptionsDto = new DataTestOptionsDto();

        dataTestOptionsDto.setDelayInSeconds( entity.getDelayInSeconds() );
        Data.MeasurementType[] measurementTypes = entity.getMeasurementTypes();
        if ( measurementTypes != null ) {
            dataTestOptionsDto.setMeasurementTypes( Arrays.copyOf( measurementTypes, measurementTypes.length ) );
        }

        return dataTestOptionsDto;
    }
}
