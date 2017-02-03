package bg.tilchev.actions;

import bg.tilchev.forms.UserForm;
import bg.tilchev.models.UserDto;
import bg.tilchev.repos.UserRepo;
import bg.tilchev.repos.UserRepoImpl;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserAction extends DispatchAction {

    private UserRepo userRepo;

    public ActionForward goToSearchPage(ActionMapping mapping, ActionForm form,
                                        HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        return mapping.findForward("search");
    }

    public ActionForward goToAddPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        return mapping.findForward("add");
    }

    public ActionForward searchUsers(ActionMapping mapping, ActionForm form,
             HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserForm userForm = (UserForm) form;
        UserRepo userRepo = this.getUserRepo();
        List<UserDto> users = userRepo.getUsersByName(userForm.getName());
        request.setAttribute("users", users);
        userForm.reset();
        return mapping.findForward("search");
    }

    public ActionForward addUser(ActionMapping mapping, ActionForm form,
             HttpServletRequest request, HttpServletResponse response) throws Exception {
        UserForm addUserForm = (UserForm) form;
        UserRepo userRepo = this.getUserRepo();
        UserDto user = new UserDto(addUserForm.getName());
        boolean wasPersisted = userRepo.persist(user);
        if(wasPersisted){
            request.setAttribute("success", true);
            addUserForm.reset();
        }else{
            request.setAttribute("failure", true);
        }
        return mapping.findForward("add");
    }

    private UserRepo getUserRepo() {
        if (this.userRepo == null) {
            this.userRepo = new UserRepoImpl();
        }
        return this.userRepo;
    }
}
