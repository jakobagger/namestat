package dk.kea.namestat.service;

import dk.kea.namestat.dto.NameStatsResponse;
import dk.kea.namestat.entity.Age;
import dk.kea.namestat.entity.Gender;
import dk.kea.namestat.entity.Nation;
import dk.kea.namestat.repository.NameStatsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NameService {

    NameStatsRepository nameStatsRepository;

    public NameService(NameStatsRepository nameStatsRepository){
        this.nameStatsRepository = nameStatsRepository;
    }

    public NameStatsResponse getInfo(String name) {

        long start = System.currentTimeMillis();

        if(nameStatsRepository.existsByName(name)){

            System.out.println("retrieved from database :"+name+" in "+(System.currentTimeMillis() - start)+" milliseconds");
            return nameStatsRepository.getByName(name);
        }

        Mono<Gender> gender = callEndpointGender(name);
        Mono<Age> age = callEndpointAge(name);
        Mono<Nation> nation = callEndpointNation(name);

        var rs = Mono.zip(age,gender,nation).map(t-> {
            NameStatsResponse response = new NameStatsResponse(t.getT1(),t.getT2(),t.getT3());
            response.setName(name);
            return response;
        });
        NameStatsResponse response = rs.block();
        nameStatsRepository.save(response);
        System.out.println("retrieved from external APIs: "+name+" in "+(System.currentTimeMillis() - start)+" milliseconds");
        return response;
    }

    private Mono<Age> callEndpointAge(String name) {
        Mono<Age> age = WebClient.create()
                .get()
                .uri("https://api.agify.io?name="+name)
                .retrieve()
                .bodyToMono(Age.class);
        return age;

    }

    private Mono<Gender> callEndpointGender(String name) {
        Mono<Gender> gender = WebClient.create()
                .get()
                .uri("https://api.genderize.io?name="+name)
                .retrieve()
                .bodyToMono(Gender.class);
        return gender;
    }

    private Mono<Nation> callEndpointNation(String name) {
        Mono<Nation> nation = WebClient.create()
                .get()
                .uri("https://api.nationalize.io?name="+name)
                .retrieve()
                .bodyToMono(Nation.class);
        return nation;
    }

}
