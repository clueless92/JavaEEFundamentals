package bg.tilchev.web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import bg.tilchev.entity.User;
import bg.tilchev.service.UserService;

@ManagedBean(name = "listUsersBean")
@ViewScoped
public class ListUsersBean {
	
	@EJB
    UserService userService;

	@PostConstruct
	public void init() {
	}

	public String editAction() {
		return "/page/editUser";
	}
	
	public String createAction() {
		return "/page/createUser";
	}
	
	public List<User> getUsers() {
		return this.userService.findAllUsersAndPostsCount();
	}
}