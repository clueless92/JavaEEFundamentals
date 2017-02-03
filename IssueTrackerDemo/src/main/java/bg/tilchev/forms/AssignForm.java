package bg.tilchev.forms;

import org.apache.struts.action.ActionForm;

public class AssignForm extends ActionForm {

    private String dispatch;
    private String userName;
    private String issueName;

    public AssignForm() {
        super();
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIssueName() {
        return this.issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getDispatch() {
        return this.dispatch;
    }

    public void setDispatch(String dispatch) {
        this.dispatch = dispatch;
    }

    public void reset() {
        this.setIssueName(null);
        this.setUserName(null);
    }
}
