package iuh.fit.se.services;

import iuh.fit.se.entities.User;
import iuh.fit.se.utils.APResponse;

public interface UserService {

	public APResponse registerUser(User user);
	public APResponse loginUser(String username, String password);


  
}
