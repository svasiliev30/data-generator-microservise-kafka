package kafka.exampl.config;

import com.esotericsoftware.yamlbeans.YamlReader;
import com.jcabi.xml.XML;
import com.jcabi.xml.XMLDocument;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Map;

@Configuration
public class BeanConfig {

    @SneakyThrows
    @Bean
    public XML producerXML() {
        return new XMLDocument(
//                new File("src/main/resources/kafka/producer.xml"));
                getClass().getResourceAsStream("/kafka/consumer.xml")
                        .readAllBytes());

    }





}
