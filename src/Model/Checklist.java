package Model;

import java.util.Map;

public class Checklist {

    String checklist_item;
    String[] function;
    String[] test_method;
    String criteria;
    Map<String, String> test_info;
    String[] status;

    public Checklist(String checklist_item, String[] function, String[] test_method, String criteria, Map<String, String> test_info, String[] status) {
        this.checklist_item = checklist_item;
        this.function = function;
        this.test_method = test_method;
        this.criteria = criteria;
        this.test_info = test_info;
        this.status = status;
    }

    public String getChecklist_item() {
        return checklist_item;
    }

    public void setChecklist_item(String checklist_item) {
        this.checklist_item = checklist_item;
    }

    public String[] getFunction() {
        return function;
    }

    public void setFunction(String[] function) {
        this.function = function;
    }

    public String[] getTest_method() {
        return test_method;
    }

    public void setTest_method(String[] test_method) {
        this.test_method = test_method;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public Map<String, String> getTest_info() {
        return test_info;
    }

    public void setTest_info(Map<String, String> test_info) {
        this.test_info = test_info;
    }

    public String[] getStatus() {
        return status;
    }

    public void setStatus(String[] status) {
        this.status = status;
    }
}

