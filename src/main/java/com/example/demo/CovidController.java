package com.example.demo;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/covid")
@RequiredArgsConstructor
public class CovidController {

    private final CovidService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Covid save(@RequestBody Covid dto){
        return service.save(dto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Covid findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping("/top5by")
    @ResponseStatus(HttpStatus.OK)
    public List<Covid> top5by(@RequestParam String by){
        return service.top5by(by);
    }

    @GetMapping("/total")
    @ResponseStatus(HttpStatus.OK)
    public int totalBy(@RequestParam String by){
        return service.getTotalCountBy(by);
    }
}
