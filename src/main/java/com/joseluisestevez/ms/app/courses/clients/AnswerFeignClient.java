package com.joseluisestevez.ms.app.courses.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-answers")
public interface AnswerFeignClient {

    @GetMapping("/student/{studentId}/exams-answered")
    Iterable<Long> getExamsAnswered(@PathVariable Long studentId);

}
