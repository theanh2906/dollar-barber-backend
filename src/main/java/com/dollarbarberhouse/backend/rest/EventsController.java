package com.dollarbarberhouse.backend.rest;

import com.dollarbarberhouse.backend.dtos.Events;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventsController {
    @GetMapping("")
    @ResponseBody
    public Flux<Events> getAllEvents() {
        WebClient client = WebClient.builder().baseUrl("https://useful-tools-api-default-rtdb.firebaseio.com/").build();
        WebClient.ResponseSpec responseSpec = client.get().uri("events.json").retrieve();
        return responseSpec.bodyToFlux(Map.class).flatMap(map -> {
            List<Events> listEvents = new ArrayList<>();
            ObjectMapper mapper = new ObjectMapper();
            map.forEach((o, o2) -> {
                Events event = mapper.convertValue(o2, Events.class);
                event.setId((String) o);
                listEvents.add(event);
            });
            Events[] events = new Events[listEvents.size()];
            return Flux.fromArray(listEvents.toArray(events));
        });
    }
}
