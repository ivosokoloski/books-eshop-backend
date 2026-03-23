package mk.ukim.finki.wp.eimt_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EimtLabApplication {

    public static void main(String[] args) {
        SpringApplication.run(EimtLabApplication.class, args);
    }

}
