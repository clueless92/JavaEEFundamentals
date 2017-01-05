package bg.tilchev.web.beans;

import java.util.Iterator;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import bg.tilchev.dto.UserDto;
import bg.tilchev.repos.UserRepo;
import bg.tilchev.web.utils.MessageUtils;

@ManagedBean(name = "editUserBean")
@ViewScoped
public class EditUserBean {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
			+ "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Inject
	HttpServletRequest request;

	@ManagedProperty("#{userRepo}")
	private UserRepo userRepo;

	private UserDto user;

	private String operationType;

	@PostConstruct
	public void init() {
		String username = request.getParameter("username");

		if (StringUtils.isBlank(username)) {
			this.user = new UserDto();
			this.setOperationType("edit");
		} else {
			for (UserDto currUser : userRepo.getUsers()) {
				if (currUser.getUsername().equals(username)) {
					this.user = currUser;
					break;
				}
			}
			this.setOperationType("update");
		}
	}

	public String updateAction() {

		if (!validate()) {
			return null;
		}

		return "/page/listUsers?faces-redirect=true";
	}

	public UserRepo getUserRepo() {
		return this.userRepo;
	}

	public void setUserRepo(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	public UserDto getUser() {
		return this.user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	protected boolean validate() {
		boolean hasErrors = false;
		if (StringUtils.isBlank(this.user.getUsername())) {
			MessageUtils.addErrorMessage("error.required.username");
			hasErrors = true;
		}

		if (StringUtils.isBlank(this.user.getPassword())) {
			MessageUtils.addErrorMessage("error.required.password");
			hasErrors = true;
		}

		if (StringUtils.isBlank(this.user.getFirstName())) {
			MessageUtils.addErrorMessage("error.required.firstname");
			hasErrors = true;
		}

		if (StringUtils.isBlank(this.user.getLastName())) {
			MessageUtils.addErrorMessage("error.required.lastname");
			hasErrors = true;
		}

		if (StringUtils.isBlank(this.user.getEmail())) {
			MessageUtils.addErrorMessage("error.required.email");
			hasErrors = true;
		}

		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		if (!pattern.matcher(this.user.getEmail()).matches()) {
			MessageUtils.addErrorMessage("error.invalid.email");
			hasErrors = true;
		}

		if (hasErrors) {
			return false;
		}

		return true;
	}

	/**
	 * Verifies if a error messages are present in the context
	 */
	public boolean hasErrors() {
		Iterator<FacesMessage> messages = FacesContext.getCurrentInstance().getMessages();
		while (messages.hasNext()) {
			FacesMessage message = messages.next();
			if (message.getSeverity().compareTo(FacesMessage.SEVERITY_ERROR) == 0) {
				return true;
			}
		}

		return false;
	}
}