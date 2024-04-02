package kafka.exampl.config;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.jcabi.xml.XML;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.yaml.snakeyaml.Yaml;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String servers;

    private final XML settings;
    @Bean
    public NewTopic temperatureTopic() {
        return TopicBuilder.name("data-temperature")
                .partitions(2)
                .replicas(1)
                .config(
                        TopicConfig.RETENTION_MS_CONFIG,
                        String.valueOf(Duration.ofDays(7).toMillis())
                )
                .build();
    }

    @Bean
    public NewTopic voltageTopic() {
        return TopicBuilder.name("data-voltage")
                .partitions(2)
                .replicas(1)
                .config(
                        TopicConfig.RETENTION_MS_CONFIG,
                        String.valueOf(Duration.ofDays(7).toMillis())
                )
                .build();
    }

    @Bean
    public NewTopic powerTopic() {
        return TopicBuilder.name("data-power")
                .partitions(2)
                .replicas(1)
                .config(
                        TopicConfig.RETENTION_MS_CONFIG,
                        String.valueOf(Duration.ofDays(7).toMillis())
                )
                .build();
    }

    @Bean
    public SenderOptions<String, Object> senderOptions() {
        Map<String, Object> props = new HashMap<>(3);
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                servers);
        props.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                new TextXPath(this.settings, "//keySerializer").toString()
        );
        props.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                new TextXPath(this.settings, "//valueSerializer").toString()
        );
        return SenderOptions.create(props);
    }

    @SneakyThrows
    @Bean
    public SenderOptions<String, Object> senderOptionsYaml() throws FileNotFoundException {
        Map<String,Object> data = new Yaml().load(
//                new FileInputStream("C:\\Project\\work\\testingJunitMock\\data-generator-microservise-kafka\\src\\main\\resources\\kafka\\kafka-config.yaml"));
                getClass().getResourceAsStream("/kafka/kafka-config.yaml").toString());
                Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                servers);
        properties.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                ((Map<String,Object>) data.get("producer")).get("key.serializer")
        );
        properties.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                ((Map<String,Object>) data.get("producer")).get("value.serializer")
        );

        return SenderOptions.create(properties);
    }

    @Bean
    @Qualifier("senderXML")
    public KafkaSender<String, Object> sender() {
        return KafkaSender.create(senderOptions());
    }

    @SneakyThrows
    @Bean
    @Qualifier("senderYaml")
    public KafkaSender<String, Object> senderYaml() {
        return KafkaSender.create(senderOptionsYaml());
    }
}
