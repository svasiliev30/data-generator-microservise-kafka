package kafka.exampl.web.mapper;

import kafka.exampl.model.Data;
import kafka.exampl.web.dto.DataDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DataMapper extends Mappable<Data, DataDto> {


}
