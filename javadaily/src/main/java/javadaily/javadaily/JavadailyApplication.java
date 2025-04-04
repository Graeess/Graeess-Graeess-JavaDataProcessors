package javadaily.javadaily;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavadailyApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavadailyApplication.class, args);
        System.out.println("Spring Boot application started at http://127.0.0.1:9091");
    }

}
