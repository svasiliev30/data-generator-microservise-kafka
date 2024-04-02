package kafka.exampl.service;

import kafka.exampl.model.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

import java.util.Random;

@Service("KafkaDataServiceImp")
@RequiredArgsConstructor
public class KafkaDataServiceImp implements KafkaDataService {

    @Autowired
    @Qualifier("senderYaml")
    private final KafkaSender<String, Object> sender;

    @Override
    public void send(Data data) {
        String topic = switch (data.getMeasurementType()) {
            case TEMPERATURE -> "data-temperature";
            case POWER -> "data-power";
            case VOLTAGE -> "data-voltage";
        };
        sender.send(
                        Mono.just(SenderRecord.create(
                                topic,
                                new Random().nextInt(2),
                                System.currentTimeMillis(),
                                String.valueOf(data.hashCode()),
                                data,
                                null)
                        )
                )
                .subscribe();
    }
}
