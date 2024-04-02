package kafka.exampl.web.mapper;

import kafka.exampl.model.test.DataTestOptions;
import kafka.exampl.web.dto.DataTestOptionsDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface DataTestOptionsMapper extends Mappable<DataTestOptions, DataTestOptionsDto> {
}
