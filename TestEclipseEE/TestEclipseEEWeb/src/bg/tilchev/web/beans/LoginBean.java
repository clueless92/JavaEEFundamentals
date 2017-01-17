package bg.tilchev.web.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import bg.tilchev.entity.Post;
import bg.tilchev.entity.User;
import bg.tilchev.service.PostService;
import bg.tilchev.service.UserService;
import bg.tilchev.web.utils.GeneralUtils;
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
	
    @EJB
    UserService userService;
    @EJB
    PostService postService;

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

	@PostConstruct
	public void init() {
    	List<User> users = this.userService.findAllUsers();
    	if(users != null && users.size() > 0) {
    		// data already seeded
    		return;
    	}
		User initUser = new User();
		initUser.setUsername("admin");
		initUser.setPassword(GeneralUtils.encodeMd5("123"));
		initUser.setFirstName("BashPesho");
		initUser.setLastName("BashPeshov");
		initUser.setEmail("bpesho@gmail.com");
		this.userService.save(initUser);
		for (int i = 0; i < 14; i++) {
			User newUser = new User();
			newUser.setUsername("user" + i);
			newUser.setPassword(GeneralUtils.encodeMd5("tajna"));
			newUser.setFirstName("Pesho" + i);
			newUser.setLastName("Peshov" + i);
			newUser.setEmail("pesho" + i + "@gmail.com");
			this.userService.save(newUser);
			for (int p = 0; p < i; p++) {
				Post newPost = new Post();
				Date date = new Date();
				newPost.setContent("Content on: " + date.toString());
				newPost.setDate(date);
				newPost.setTitle("Title on: " + date.toString());
				newPost.setAuthor(newUser);
				postService.save(newPost);
			}
		}
	}

	public String login() {
		String encryptedPass = GeneralUtils.encodeMd5(this.password);
		User user = this.userService.loginUser(this.username, encryptedPass);

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
