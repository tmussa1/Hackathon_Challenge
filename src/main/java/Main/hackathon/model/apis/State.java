package Main.hackathon.model.apis;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class State {

    @Id
    @GeneratedValue
    private long id;

    private String state;

    private String area;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public State(String state) {
        this.state = state;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public State() {
    }

    public State(String state, String area) {
        this.state = state;
        this.area = area;
    }
}
