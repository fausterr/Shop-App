package app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.dto.UserDTO;
import app.model.User;
import app.model.UserRole;
import app.repository.UserRepository;
import app.repository.UserRoleRepository;

@Service
public class UserService {
	
	private static final String DEFAULT_ROLE = "ROLE_USER";
	private static final String ADMIN_ROLE = "ROLE_ADMIN";
	private UserRepository userRepository;
	private UserRoleRepository roleRepository;
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Autowired
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Autowired
	public void setRoleRepository(UserRoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public void changePassword(UserDTO user) {
		String encodePassword = passwordEncoder.encode(user.getNewPassword());
		userRepository.changePasswordByLogin(encodePassword, user.getLogin());
	}
	
	public boolean checkPassword(String oldPassword) {
		CharSequence charSequence = new StringBuffer(oldPassword);
		String currentPass = userRepository.findByLogin(getCurrentUser()).getPassword();
		if(passwordEncoder.matches(charSequence, currentPass)) {
			return true;
		} else {
			return false;
		}
	}	
	
	public User getUserByLogin(String login) {
		User user = userRepository.findByLogin(login);
		return user;
	}
	
	public static String getCurrentUser() {
		SecurityContext sc = SecurityContextHolder.getContext();
		String login = sc.getAuthentication().getName();
		return login;
	}
	
	public void addWithDefaultRole(User user) {
		UserRole defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
		user.getRoles().add(defaultRole);
		String passwordHash = passwordEncoder.encode(user.getPassword());		
		user.setPassword(passwordHash);
		userRepository.save(user);
	}
	
	public void addAdminWithAdminRole(User user) {
		UserRole adminRole = roleRepository.findByRole(ADMIN_ROLE);
		user.getRoles().add(adminRole);
		String passwordHash = passwordEncoder.encode(user.getPassword());
		user.setPassword(passwordHash);
		userRepository.save(user);
	}

	public static boolean hasRole (String roleName) {
	    return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
	            .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(roleName));
	}
}