package org.jara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AgentOne {
    public static void main(String[] args) {
        SpringApplication.run(AgentOne.class, args);
    }
}
