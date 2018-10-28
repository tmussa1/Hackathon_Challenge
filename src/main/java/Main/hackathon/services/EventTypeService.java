package Main.hackathon.services;

import Main.hackathon.model.apis.EventTypeApi.EventType;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EventTypeService {

    public EventType getAllEventTypes(){
        RestTemplate restTemplate = new RestTemplate();
        EventType eventType = restTemplate.getForObject("https://api.weather.gov/alerts/types", EventType.class);
        return eventType;
    }
}
