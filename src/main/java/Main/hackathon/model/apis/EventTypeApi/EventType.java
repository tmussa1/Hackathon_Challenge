package Main.hackathon.model.apis.EventTypeApi;

import Main.hackathon.model.apis.EventTypeApi.Event;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventType {

    @JsonProperty("eventTypes")
    List<Event>events;

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public EventType() {
        this.events = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "EventType{" +
                "events=" + events +
                '}';
    }
}
