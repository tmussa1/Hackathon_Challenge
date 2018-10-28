package Main.hackathon.model.apis.AlertApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {

    private String messageType;
    private String severity;
    private String certainty;
    private String urgency;
    private String event;
    private String areaDesc;
    private String [] affectedZones;
    private String instruction;

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getCertainty() {
        return certainty;
    }

    public void setCertainty(String certainty) {
        this.certainty = certainty;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String[] getAffectedZones() {
        return affectedZones;
    }

    public void setAffectedZones(String[] affectedZones) {
        this.affectedZones = affectedZones;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    @Override
    public String toString() {
        return "Properties{" +
                "messageType='" + messageType + '\'' +
                ", severity='" + severity + '\'' +
                ", certainty='" + certainty + '\'' +
                ", urgency='" + urgency + '\'' +
                ", event='" + event + '\'' +
                ", areaDesc='" + areaDesc + '\'' +
                ", affectedZones=" + Arrays.toString(affectedZones) +
                ", instruction='" + instruction + '\'' +
                '}';
    }
}
