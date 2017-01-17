package bg.tilchev.web.beans;

import java.util.Iterator;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import bg.tilchev.entity.User;
import bg.tilchev.service.UserService;
import bg.tilchev.web.utils.GeneralUtils;
import bg.tilchev.web.utils.MessageUtils;

@ManagedBean(name = "editUserBean")
@ViewScoped
public class EditUserBean {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\."
			+ "[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	@Inject
	HttpServletRequest request;
	
	@EJB
    UserService userService;

	private User user;

	private String operationType;

	@PostConstruct
	public void init() {
		String id = request.getParameter("id");
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			this.user = userService.findById(Long.parseLong(id));
		}
	}

	public String updateAction() {
		if (!this.validate()) {
			return null;
		}
		String encryptedPass = GeneralUtils.encodeMd5(this.user.getPassword());
		this.user.setPassword(encryptedPass);
		userService.update(this.user);

		return "/page/listUsers?faces-redirect=true";
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
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