package Main.hackathon.services;

import Main.hackathon.model.apis.AlertApi.Features;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Service
@Component
public class AlertService {

    public Features getAlertByState(String state){
        RestTemplate restTemplate = new RestTemplate();
        Features features= restTemplate.getForObject("https://api.weather.gov/alerts?area=" + state, Features.class);
        System.out.println("Features " + features);
        return features;
    }

}
