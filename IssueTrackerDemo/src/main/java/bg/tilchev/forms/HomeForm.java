package bg.tilchev.forms;

import org.apache.struts.action.ActionForm;

public class HomeForm extends ActionForm {

    private String greeting;

    public HomeForm() {
        super();
    }

    public String getGreeting() {
        return this.greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

}
