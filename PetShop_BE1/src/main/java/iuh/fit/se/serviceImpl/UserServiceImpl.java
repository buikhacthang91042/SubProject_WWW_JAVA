package iuh.fit.se.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iuh.fit.se.dtos.RegisterDTO;
import iuh.fit.se.entities.User;
import iuh.fit.se.repository.UserRepository;
import iuh.fit.se.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean validateLogin(String email, String password) {
    	User user = userRepository.findByEmailAddress(email).orElse(null);
    	if (user == null) {
    	    return false; // Hoặc xử lý khác
    	}

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, user.getPassword());
    }

    
   

    @Override
    public boolean registerUser(RegisterDTO registerDTO) {
        if (userRepository.findByEmailAddress(registerDTO.getEmailAddress()) != null) {
            throw new RuntimeException("Email đã được sử dụng!");
        }
        if (userRepository.findByUsername(registerDTO.getUsername()) != null) {
            throw new RuntimeException("Username đã được sử dụng!");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(registerDTO.getPassword());

        User newUser = new User();
        newUser.setName(registerDTO.getName());
        newUser.setUsername(registerDTO.getUsername());
        newUser.setEmailAddress(registerDTO.getEmailAddress());
        newUser.setPassword(encodedPassword);
        newUser.setRole("CUSTOMER");

        userRepository.save(newUser);
        return true;
    }

    
    @Override
    public void updateUser(User updatedUser) {
        User existingUser = userRepository.findById(updatedUser.getId()).orElseThrow(() -> new RuntimeException("Không tìm thấy user!"));
        existingUser.setName(updatedUser.getName());
        existingUser.setPassword(updatedUser.getPassword());
        userRepository.save(existingUser);
    }


}