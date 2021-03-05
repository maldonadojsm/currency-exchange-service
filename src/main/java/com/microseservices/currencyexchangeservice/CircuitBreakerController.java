package com.microseservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger =
            LoggerFactory.getLogger(CircuitBreakerController.class);

    /*
    @Retry
    1. Configure properties of retry routine in application.properties
    2. Provided fallbackMethod must exactly match method name
    @CircuitBreaker States
    1. Closed -> Everything is working fine
    2. Open -> Failure Rate Above Threshold. Start sending response to previous previous service that next service is down
    3. Half-Open -> Keep sending responses back but keep attempting to contact microservice to verify it's back up
    @RateLimiter
    As name suggests, limit the amount of calls in a given time interval to an API before it triggers circuit breaker
    Limits configured in application.properties
    @BulkHead
    How many concurrent calls are allowed to API
     */
    @GetMapping("/sample-api")
    //@Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    //@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    //@RateLimiter(name = "default", fallbackMethod = "hardcodedResponse")
    @Bulkhead(name = "default")
    public String sampleApi(){
        logger.info("Sample API Call Received");
//        ResponseEntity<String> forEntity = new RestTemplate()
//                .getForEntity("http://localhost:8000/some-dummy-url", String.class);
        return "sample-api"; //forEntity.getBody();
    }

    public String hardcodedResponse(Exception ex){
        return "fallback-response";
    }
}
