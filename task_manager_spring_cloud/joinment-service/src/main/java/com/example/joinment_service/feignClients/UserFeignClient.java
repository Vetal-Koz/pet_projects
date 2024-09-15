package com.example.joinment_service.feignClients;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(url = "${user.service.url}", value = "user-feign-client", path = "/api/users")
public interface UserFeignClient {
}
