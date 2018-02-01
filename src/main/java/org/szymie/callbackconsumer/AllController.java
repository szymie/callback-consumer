package org.szymie.callbackconsumer;

import org.slf4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class AllController {

    private Logger logger;

    private AtomicLong counter;

    public AllController(Logger logger) {
        this.logger = logger;
        counter = new AtomicLong(0);
    }

    @RequestMapping("/**")
    public <T> ResponseEntity<?> all(HttpMethod method, HttpEntity<T> entity, HttpServletRequest request) {
        long requestId = counter.getAndIncrement();
        logger.info("Request {}, method: {}, body: {}", requestId, method.name(), entity.getBody());
        logger.info("Request {}, headers: {}", requestId, entity.getHeaders());
        logger.info("Request {}, URI: {}", requestId, request.getRequestURI());
        return ResponseEntity.ok().build();
    }
}
