package bg.tilchev.web.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import bg.tilchev.dto.UserDto;
import bg.tilchev.repos.UserRepo;
import bg.tilchev.web.utils.MessageUtils;

@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final String SUCCESS_LOGIN_REDIRECT = "/page/success?faces-redirect=true";
	private static final String LOGIN_PAGE_REDIRECT = "/page/login?faces-redirect=true";

	// beans.xml need to be added in WEB-INF in order CDI to work
	@Inject
	private HttpServletRequest request;
	
	@ManagedProperty("#{userRepo}")
	private UserRepo userRepo;

	private String username;
	private String password;

	public LoginBean() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
    public UserRepo getUserRepo() {
        return this.userRepo;
    }

    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

	@PostConstruct
	public void init() {
		// TODO
	}

	public String login() {
		UserDto user = this.userRepo.validateUser(this.username, this.password);

        if (user == null) {
            MessageUtils.addErrorMessage("login.error.invalid.credentials");
            return "";
        } else {
            // If we want to avoid using CDI for accessing the request we can
            // take it from FacesCondex by using:
            // HttpServletRequest request =
            // (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
            this.request.getSession().setAttribute("LOGGED_USER", user);
            return SUCCESS_LOGIN_REDIRECT;
        }
	}
	
	public String logout() {
		this.request.getSession().invalidate();
		return LOGIN_PAGE_REDIRECT;
	}
}
