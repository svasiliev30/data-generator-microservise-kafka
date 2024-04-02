package kafka.exampl.web.controller;

import kafka.exampl.model.Data;
import kafka.exampl.model.test.DataTestOptions;
import kafka.exampl.service.KafkaDataService;
import kafka.exampl.service.KafkaDataServiceImp;
import kafka.exampl.service.TestDataServise;
import kafka.exampl.web.dto.DataDto;
import kafka.exampl.web.dto.DataTestOptionsDto;
import kafka.exampl.web.mapper.DataMapper;
import kafka.exampl.web.mapper.DataTestOptionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/data")
public class DataController {

    @Autowired
    @Qualifier("KafkaDataServiceImp")
    private KafkaDataService kafkaDataService;

    @Autowired
    @Qualifier("TestDataServiseImpl")
    private TestDataServise testDataServise;

    @Autowired
    private DataMapper dataMapper;

    @Autowired
    private DataTestOptionsMapper dataTestOptionsMapper;


    @PostMapping("/send")
    public void send(@RequestBody DataDto dto) {
        Data data = dataMapper.toEntity(dto);
        kafkaDataService.send(data);
    }

    @PostMapping("/test/send")
    public void testSend(@RequestBody DataTestOptionsDto testOptionsDto) {
        DataTestOptions testOptions = dataTestOptionsMapper.toEntity(testOptionsDto);
        testDataServise.sendMessages(testOptions);

    }
}
