package Main.hackathon;

import Main.hackathon.model.apis.AlertApi.Alert;

import java.util.Comparator;

public class AlertComparator implements Comparator<Alert> {
    @Override
    public int compare(Alert o1, Alert o2) {
        return o1.getProperties().getEvent().compareTo(o1.getProperties().getEvent());
    }
}
