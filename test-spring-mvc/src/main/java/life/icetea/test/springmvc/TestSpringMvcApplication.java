package life.icetea.test.springmvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author icetea
 */
@SpringBootApplication
@EnableAsync
public class TestSpringMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSpringMvcApplication.class);
    }

}
