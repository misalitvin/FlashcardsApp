package pl.edu.pja.tpo02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@ConfigurationPropertiesScan

public class FlashcardsApp {

    public static void main(String[] args) throws Throwable {
        ConfigurableApplicationContext context = SpringApplication.run(FlashcardsApp.class);
        FlashcardsController controller = context.getBean(FlashcardsController.class);
        controller.start();
    }
}
