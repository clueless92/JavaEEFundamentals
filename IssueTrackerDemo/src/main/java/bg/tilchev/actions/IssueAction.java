package bg.tilchev.actions;

import bg.tilchev.forms.IssueForm;
import bg.tilchev.models.IssueDto;
import bg.tilchev.repos.IssueRepo;
import bg.tilchev.repos.IssueRepoImpl;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class IssueAction extends DispatchAction {

    private IssueRepo issueRepo;

    public ActionForward goToSearchPage(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("search");
    }

    public ActionForward goToAddPage(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("add");
    }

    public ActionForward searchIssues(ActionMapping mapping, ActionForm form,
                                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        IssueForm searchIssueForm = (IssueForm) form;
        IssueRepo issueRepo = this.getIssueRepo();
        List<IssueDto> issues = issueRepo.getIssuesByName(searchIssueForm.getName());
        request.setAttribute("issues", issues);
        searchIssueForm.reset();
        return mapping.findForward("search");
    }

    public ActionForward addIssue(ActionMapping mapping, ActionForm form,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {
        IssueForm addIssueForm = (IssueForm) form;
        IssueRepo issueRepo = this.getIssueRepo();
        IssueDto issue = new IssueDto(addIssueForm.getName());
        issue.setPriority(addIssueForm.getPriority());
        issue.setState(addIssueForm.getState());
        issue.setProject(addIssueForm.getProject());
        issue.setType(addIssueForm.getType());
        issue.setSprint(addIssueForm.getSprint());
        issue.setStoryPoints(addIssueForm.getStoryPoints());
        boolean wasPersisted = issueRepo.persist(issue);
        if(wasPersisted){
            request.setAttribute("success", true);
            addIssueForm.reset();
        }else{
            request.setAttribute("failure", true);
        }
        return mapping.findForward("add");
    }

    private IssueRepo getIssueRepo() {
        if (this.issueRepo == null) {
           this.issueRepo = new IssueRepoImpl();
        }
        return this.issueRepo;
    }
}
