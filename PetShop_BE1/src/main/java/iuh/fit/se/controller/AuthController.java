package iuh.fit.se.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iuh.fit.se.dtos.LoginDTO;
import iuh.fit.se.dtos.RegisterDTO;
import iuh.fit.se.services.UserService;

@RestController
public class AuthController {
	 @Autowired
	    private UserService userService;

	    @PostMapping("/login")
	    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
	        boolean isValid = userService.validateLogin(loginDTO.getEmail(), loginDTO.getPassword());
	        if (isValid) {
	            return ResponseEntity.ok("Đăng nhập thành công!");
	        }
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email hoặc mật khẩu không đúng!");
	    }
	    
	    @PostMapping("/register")
	    public ResponseEntity<?> register(@RequestBody RegisterDTO registerDTO) {
	        boolean isRegistered = userService.registerUser(registerDTO);
	        if (isRegistered) {
	            return ResponseEntity.ok("Đăng ký thành công!");
	        }
	        return ResponseEntity.badRequest().body("Email hoặc Username đã tồn tại!");
	    }
	}