package Main.hackathon.model.apis;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InformationExpert {

    private Map<String,List<Phone>> stateToListOfPhones;
    private Map<String,List<Phone>> areaToListOfPhones;
    private Map<Integer, Dispatcher> zipcodeToDispatcher;

    public InformationExpert(){
        this.stateToListOfPhones = new HashMap<>();
        this.areaToListOfPhones = new HashMap<>();
        this.zipcodeToDispatcher = new HashMap<>();

        returnPhonesByState();
        returnPhonesByArea();
        returnZipcodeToDispatcher();
    }

    public Map<String,List<Phone>> returnPhonesByState() {
        List<Phone>phones = new ArrayList<>();
        phones.add(new Phone("+12028404694"));
        phones.add(new Phone("+16146808118"));
        phones.add(new Phone("+16145928099"));
        phones.add(new Phone("+12025849311"));
        phones.add(new Phone("+12025091187"));
        stateToListOfPhones.put("OH", phones);
        return stateToListOfPhones;
    }

    public  Map<String,List<Phone>> returnPhonesByArea() {
        List<Phone>phones = new ArrayList<>();
        phones.add(new Phone("+12028404694"));
        phones.add(new Phone("+16146808118"));
        areaToListOfPhones.put("Franklin", phones);

        List<Phone>phones2 = new ArrayList<>();
        phones2.add(new Phone("+16145928099"));
        phones2.add(new Phone("+12025849311"));
        areaToListOfPhones.put("Fairfield", phones2);

        List<Phone>phones3 = new ArrayList<>();
        phones3.add(new Phone("+12025091187"));
        areaToListOfPhones.put("Montgomery", phones3);

        return areaToListOfPhones;
    }

    private Map<Integer, Dispatcher> returnZipcodeToDispatcher() {
        for(int i = 43000; i < 46000; i++){
            if(i < 43750){
                zipcodeToDispatcher.put(i, new Dispatcher("9 W Main St, Alexandria, OH 43001", "Alex"));
            } else if(i < 44500){
                zipcodeToDispatcher.put(i, new Dispatcher("68 1/2 West Main Street, New Concord, OH 43762", "Sam"));
            } else if(i < 46000){
                zipcodeToDispatcher.put(i, new Dispatcher("118 E 6th St, Cincinnati, OH 45202", "Mark"));
            }
        }
        return zipcodeToDispatcher;
    }

}
