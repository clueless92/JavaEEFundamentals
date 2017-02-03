package bg.tilchev.forms;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm {

    private String dispatch;
    private String name;

    public UserForm() {
        super();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDispatch() {
        return this.dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public void reset() {
        this.setName(null);
    }
}
