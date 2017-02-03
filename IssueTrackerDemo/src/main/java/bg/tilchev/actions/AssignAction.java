package bg.tilchev.actions;

import bg.tilchev.forms.AssignForm;
import bg.tilchev.models.Issue;
import bg.tilchev.models.User;
import bg.tilchev.repos.IssueRepo;
import bg.tilchev.repos.IssueRepoImpl;
import bg.tilchev.repos.UserRepo;
import bg.tilchev.repos.UserRepoImpl;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class AssignAction extends DispatchAction {


    private IssueRepo issueRepo;
    private UserRepo userRepo;

    public ActionForward goToAssignPage(
            ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return mapping.findForward("assign");
    }


    public ActionForward assignIssueToUser(
            ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        AssignForm assignForm = (AssignForm) form;
        IssueRepo issueRepo = this.getIssueRepo();
        UserRepo userRepo = this.getUserRepo();
        Issue issue = this.issueRepo.getIssueByExactName(assignForm.getIssueName());
        issueRepo.closeConnection();
        User user = this.userRepo.getUserByExactName(assignForm.getUserName());
        Set<Issue> issues = user.getIssues();
        issues.add(issue);
        user.setIssues(issues);
        boolean wasPersisted = userRepo.persist(user);
        if (wasPersisted) {
            request.setAttribute("success", true);
            assignForm.reset();
        } else {
            request.setAttribute("failure", true);
        }
        return mapping.findForward("assign");
    }

    private IssueRepo getIssueRepo() {
        if (this.issueRepo == null) {
            this.issueRepo = new IssueRepoImpl();
        }
        return this.issueRepo;
    }

    private UserRepo getUserRepo() {
        if (this.userRepo == null) {
            this.userRepo = new UserRepoImpl();
        }
        return this.userRepo;
    }
}
