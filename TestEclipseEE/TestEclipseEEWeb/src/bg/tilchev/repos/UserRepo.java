package bg.tilchev.repos;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.lang3.StringUtils;
import bg.tilchev.dto.UserDto;

@ManagedBean(name = "userRepo")
@SessionScoped
public class UserRepo {
	
	private List<UserDto> users;

	@PostConstruct
	public void init() {
		// initialize the users list
		this.users = new ArrayList<UserDto>();
		// create user
		UserDto initUser = new UserDto("admin", "123", "Pesho", "Peshov", "pesho@gmail.com");
		// add user the created user to the list
		this.users.add(initUser);
		for (int i = 0; i < 14; i++) {
			UserDto newUser = new UserDto("user" + i, "tajna", "Pesho" + i, "Peshov" + i, "pesho" + i + "@gmail.com");
			this.users.add(newUser);
		}
	}

	public UserDto validateUser(String username, String password) {
		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return null;
		}
		for (UserDto currentUser : this.users) {
			if (username.equals(currentUser.getUsername()) && password.equals(currentUser.getPassword())) {
				return currentUser;
			}
		}
		return null;
	}

	public List<UserDto> getUsers() {
		return this.users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}
}