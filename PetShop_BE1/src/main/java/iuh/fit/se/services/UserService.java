package iuh.fit.se.services;

import iuh.fit.se.dtos.RegisterDTO;
import iuh.fit.se.entities.User;

public interface UserService {
	public  boolean validateLogin(String email, String password);
	public boolean registerUser(RegisterDTO registerDTO);
	public void updateUser(User updatedUser);
}
