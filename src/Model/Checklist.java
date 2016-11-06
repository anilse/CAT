package Model;

import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Checklist {

    String checklist_item;
    String[] function;
    Map<String, String> check_steps;
    String[] check_step;
    String status;

    public Checklist(String checklist_item, String[] function, Map<String, String> check_steps, String status) {
        this.checklist_item = checklist_item;
        this.function = function;
        this.check_steps = check_steps;
        this.status = status;
    }

    public Checklist() {

    }

    public String getChecklist_item() {
        return checklist_item;
    }

    @XmlElement
    public void setChecklist_item(String checklist_item) {
        this.checklist_item = checklist_item;
    }


    public void setFunction(String[] function) {
        this.function = function;
    }

    public Map<String, String> getCheck_steps() {
        return check_steps;
    }

    @XmlElement
    public void setCheck_steps(Map<String, String> check_steps) {

       //String check_step =  check_steps.get(function);
    }

    public String getStatus() {
        return status;
    }

    @XmlElement
    public void setStatus(String status) {
        this.status = status;
    }
}

