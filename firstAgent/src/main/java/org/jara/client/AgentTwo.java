package org.jara.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "AgentTwo", url = "http://localhost:8081")
public interface AgentTwo {
}
