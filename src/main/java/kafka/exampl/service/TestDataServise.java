package kafka.exampl.service;

import kafka.exampl.model.test.DataTestOptions;

public interface TestDataServise {
    void sendMessages(DataTestOptions testOptions);
}
