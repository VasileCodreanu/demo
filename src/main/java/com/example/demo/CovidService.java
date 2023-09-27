package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CovidService {

    private final CovidRepository repository;


    public Covid save(Covid dto) {
        return repository.save(dto);
    }

    public Covid findById(Long id) {
        return repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatusCode.valueOf(404)));
    }


    public List<Covid> top5by(String fieldName) {
        List<Covid> all = repository.findAll();

        switch (fieldName){
            case "id" : return all.stream()
                    .sorted(Comparator.comparingLong(Covid::getId).reversed())
                    .limit(5)
                    .toList();
            case "deaths" : return all.stream()
                    .sorted(Comparator.comparingInt(Covid::getDeaths).reversed())
                    .limit(5)
                    .toList();
            case "active" : return all.stream()
                    .sorted(Comparator.comparingInt(Covid::getActive).reversed())
                    .limit(5)
                    .toList();
            case "recovered" : return all.stream()
                    .sorted(Comparator.comparingInt(Covid::getRecovered).reversed())
                    .limit(5)
                    .toList();
            case "country" : return all.stream()
                    .sorted(Comparator.comparing(Covid::getCountry))
                    .limit(5)
                    .toList();
            default: throw new ResponseStatusException(HttpStatusCode.valueOf(400));
        }
    }

    public Integer getTotalCountBy(String fieldName) {
        List<Covid> all = repository.findAll();

        switch (fieldName){
            case "deaths" : return all.stream()
                    .map(Covid::getDeaths)
                    .reduce(0, (a, b) -> a + b );

            case "active" : return all.stream()
                    .map(Covid::getActive)
                    .reduce(0, (a, b) -> a + b );

            case "recovered" : return all.stream()
                    .mapToInt(x -> x.getRecovered())
                    .sum();

            case "country" : return all.size();

            default: throw new ResponseStatusException(HttpStatusCode.valueOf(400));
        }
    }
}
