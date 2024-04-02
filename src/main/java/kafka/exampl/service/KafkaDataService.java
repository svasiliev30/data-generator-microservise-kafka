package kafka.exampl.service;

import kafka.exampl.model.Data;

public interface KafkaDataService {
    void send(Data data);
}
