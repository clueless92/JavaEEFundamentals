package bg.tilchev.web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import bg.tilchev.dto.UserDto;
import bg.tilchev.repos.UserRepo;

@ManagedBean(name = "listUsersBean")
@ViewScoped
public class ListUsersBean {
	
	@ManagedProperty("#{userRepo}")
	private UserRepo userRepo;

	@PostConstruct
	public void init() {
	}

	public String editAction() {
		return "/page/editUser";
	}
	
	public String createAction() {
		return "/page/createUser";
	}
	
	public List<UserDto> getUsers() {
		return this.userRepo.getUsers();
	}

	public UserRepo getUserRepo() {
		return this.userRepo;
	}

	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
}