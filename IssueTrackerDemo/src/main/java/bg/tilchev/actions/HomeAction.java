package bg.tilchev.actions;

import bg.tilchev.forms.HomeForm;
import bg.tilchev.models.Issue;
import bg.tilchev.models.User;
import bg.tilchev.repos.UserRepo;
import bg.tilchev.repos.UserRepoImpl;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class HomeAction extends Action {

    private static final String GREETING = "Issue Tracker Demo with Struts 1.3";

    private UserRepo userRepo;

    @Override
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        HomeForm homeForm = (HomeForm) form;
        homeForm.setGreeting(GREETING);
        this.seedData();
        return mapping.findForward("home");
    }

    private void seedData() {
        UserRepo userRepo = this.getUserRepo();
        if(userRepo.getUsersByName("").size() != 0) {
            return;
        }
        Set<Issue> issues = new HashSet<Issue>();
        for (int i = 0; i < 10; i++) {
            Issue issue = new Issue("Issue" + i);
            issue.setStoryPoints(i);
            issue.setSprint(i);
            issue.setProject("Project" + i);
            if (i % 2 == 0) {
                issue.setState("Reopened");
                issue.setPriority("Critical");
                issue.setType("Bug");
            } else {
                issue.setState("Open");
                issue.setPriority("Normal");
                issue.setType("Task");
            }
            issues.add(issue);
            User user = new User("Pesho" + i);
            user.setIssues(new HashSet<Issue>(issues));
            userRepo.persist(user);
        }
    }

    private UserRepo getUserRepo() {
        if (this.userRepo == null) {
            this.userRepo = new UserRepoImpl();
        }
        return this.userRepo;
    }
}
