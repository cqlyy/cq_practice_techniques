package cq.techniques;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 文档启动类!
 */
@SpringBootApplication
@EnableEurekaClient
public class DocumentApplication {
    public static void main(String[] args) {
        SpringApplication.run(DocumentApplication.class,args);
    }
}
