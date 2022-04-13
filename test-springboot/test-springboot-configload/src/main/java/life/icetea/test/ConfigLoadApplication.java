package life.icetea.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author icetea
 */
@SpringBootApplication
public class ConfigLoadApplication implements ApplicationRunner {

    @Value("${icetea.test}")
    private String test;

    @Value("${test.name}")
    private String testName;

    public static void main(String[] args) {
        SpringApplication.run(ConfigLoadApplication.class);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println(test);
        System.out.println(testName);
    }
}
