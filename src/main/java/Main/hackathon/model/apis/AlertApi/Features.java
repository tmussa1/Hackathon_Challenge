package Main.hackathon.model.apis.AlertApi;

import Main.hackathon.model.apis.AlertApi.Alert;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Features {

    @JsonProperty("features")
    private List<Alert> alerts;

    public Features() {
        this.alerts = new ArrayList<>();
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }
}
