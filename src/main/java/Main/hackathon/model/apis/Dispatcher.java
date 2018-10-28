package Main.hackathon.model.apis;

import org.springframework.stereotype.Component;

@Component
public class Dispatcher {

    private String dispatcherAddress;
    private String officerName;

    public String getDispatcherAddress() {
        return dispatcherAddress;
    }

    public void setDispatcherAddress(String dispatcherAddress) {
        this.dispatcherAddress = dispatcherAddress;
    }

    public String getOfficerName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public Dispatcher(String dispatcherAddress, String officerName) {
        this.dispatcherAddress = dispatcherAddress;
        this.officerName = officerName;
    }

    public Dispatcher() {
    }
}
