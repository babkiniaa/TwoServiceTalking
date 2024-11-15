package org.jara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgentTwo {
    public static void main(String[] args) {
        SpringApplication.run(AgentTwo.class, args);
    }
}
