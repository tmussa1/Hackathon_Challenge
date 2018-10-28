package Main.hackathon.controller;

import Main.hackathon.AlertComparator;
import Main.hackathon.model.apis.AlertApi.Alert;
import Main.hackathon.model.apis.AlertApi.Features;
import Main.hackathon.model.apis.EventTypeApi.Event;
import Main.hackathon.model.apis.EventTypeApi.EventType;
import Main.hackathon.model.apis.InformationExpert;
import Main.hackathon.model.apis.Phone;
import Main.hackathon.model.apis.State;
import Main.hackathon.repositories.EventTypeRepository;
import Main.hackathon.repositories.StateRepository;
import Main.hackathon.services.AlertService;
import Main.hackathon.services.EventTypeService;
import ch.qos.logback.core.net.SyslogOutputStream;
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.internal.guava.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.StreamSupport;
import com.twilio.*;

@Controller
public class AlertController {

    @Autowired
    AlertService alertService;

    @Autowired
    EventTypeService eventTypeService;

    @Autowired
    EventTypeRepository eventTypeRepository;

    @Autowired
    StateRepository stateRepository;


    public static final String ACCOUNT_SID = "ACe7b941197cda7dd5e148043d3815c81b";
    public static final String AUTH_TOKEN = "72cd328a023cc0183aa6abeb4cd9bf3f";
    public static final String TWILIO_NUMBER = "+18503188473";

    @RequestMapping("/")
    public String homePage(Model model){
        model.addAttribute("astate", new State());
        return "index";
    }
    @PostMapping("/getstate")
    public String getAlert(@ModelAttribute("astate") State state){

        Features features = null;

        features  = alertService.getAlertByState(state.getState());

        List<Alert> alertList = features.getAlerts();

        Set<Alert> alertSet = new HashSet<>(alertList);

        TreeSet<Alert> alerts = new TreeSet<Alert>(new AlertComparator());
        alerts.addAll(alertSet);

        Iterable<Event> eventTypes = eventTypeRepository.findAll();
        List<Event> eventList = Lists.newArrayList(eventTypes);


        outer:
        for(Alert alert : alerts){
            inner:
            for(int k = 0; k < eventList.size(); k++){
                if(alert.getProperties().getEvent().contains(eventList.get(k).getEvent()) && alert != alerts.higher(alert)){
                    sendAlerts(alert, state);
                    break inner;
                }
            }
        }


        return "SendAlert";
    }

    public void sendAlerts(Alert alert, State state){

        String [] areas = alert.getProperties().getAreaDesc().split(";");

        State state2 = stateRepository.findByArea(state.getArea());
        sendAlertsTwilio(alert, state2);
    }

    public void sendAlertsTwilio(Alert alert, State state){

        InformationExpert informationExpert = new InformationExpert();

        List<Phone> phones = informationExpert.returnPhonesByState().get(state.getState());

        try {
            TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID, AUTH_TOKEN);

                List<NameValuePair> params = new ArrayList<NameValuePair>();

                params.add(new BasicNameValuePair("Body", alert.getProperties().getEvent() + " is happening with "
                + alert.getProperties().getCertainty() + " and it is " + alert.getProperties().getUrgency()));
                params.add(new BasicNameValuePair("To", phones.get(0).getPhoneNumber()));
                params.add(new BasicNameValuePair("From", TWILIO_NUMBER));

                MessageFactory messageFactory = client.getAccount().getMessageFactory();
                Message message = messageFactory.create(params);

                List<NameValuePair> params2 = new ArrayList<NameValuePair>();

                params2.add(new BasicNameValuePair("Body", alert.getProperties().getEvent() + " is happening with "
                        + alert.getProperties().getCertainty() + " and it is " + alert.getProperties().getUrgency() +
                " . SEND 'YES' IF YOU ARE IN NEED OF EMERGENCY SERVICES"));
                params2.add(new BasicNameValuePair("To", phones.get(1).getPhoneNumber()));
                params2.add(new BasicNameValuePair("From", TWILIO_NUMBER));

                MessageFactory messageFactory2 = client.getAccount().getMessageFactory();
                Message message2 = messageFactory2.create(params2);

        } catch(TwilioRestException e){
            System.out.println(e.getErrorMessage());
        }
    }
//
//    @RequestMapping(value = "/viewresponses", method = RequestMethod.POST, consumes = "text/plain")
    @RequestMapping(value = {"sms"},method = RequestMethod.POST)
    public String replyMessage(HttpServletRequest request, HttpServletResponse respons,
                               @RequestParam("Body") String message, @RequestParam("From") String from,
                               @RequestParam("FromZip") String zipcode) throws IOException {

        System.out.println("The response is = " + message);
        return "response";
    }


    @RequestMapping("/eventtype")
    public EventType getAllEventTypes(){
        return eventTypeService.getAllEventTypes();
    }

    @PostConstruct
    public void dataLoader(){
        eventTypeRepository.saveAll(eventTypeService.getAllEventTypes().getEvents());

        InformationExpert informationExpert = new InformationExpert();

        for(String area : informationExpert.returnPhonesByArea().keySet()) {
            stateRepository.save(new State("OH",area));
        }
    }

}
