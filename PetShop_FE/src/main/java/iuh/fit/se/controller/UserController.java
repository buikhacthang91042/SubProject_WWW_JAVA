package iuh.fit.se.controller;

import iuh.fit.se.entities.User;
import iuh.fit.se.services.UserService;
import iuh.fit.se.utils.APResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping("/")
    public String showHomePage() {
        return "index"; 
    }
    
    @GetMapping("/loginPage")
    public String goToLoginPage() {
        return "login";  // Trả về view "login.html"
    } 
    
    @GetMapping("/registrationPage")
    public String goToRegistrationPage() {
        return "registration";  // Trả về view "registration.html"
    }
    
    // Đăng ký người dùng
    @PostMapping("/register")
    public ModelAndView registerUser(User user) {
        APResponse response = userService.registerUser(user);

        ModelAndView modelAndView = new ModelAndView();
        if (response.getStatus() == 200) {
            // Nếu đăng ký thành công, hiển thị trang xác nhận
            modelAndView.setViewName("registrationSuccess"); // Tên view (ví dụ: registrationSuccess.jsp)
            modelAndView.addObject("message", "Đăng ký thành công!");
        } else {
            // Nếu có lỗi, hiển thị thông báo lỗi
            modelAndView.setViewName("registrationError"); // Tên view (ví dụ: registrationError.jsp)
            modelAndView.addObject("message", "Đăng ký thất bại! " + response.getMessage());
        }
        return modelAndView;
    }

    // Đăng nhập người dùng
    @PostMapping("/login")
    public ModelAndView loginUser(@RequestParam String username, @RequestParam String password) {
        APResponse response = userService.loginUser(username, password);

        ModelAndView modelAndView = new ModelAndView();
        if (response.getStatus() == 200) {
            // Nếu đăng nhập thành công, hiển thị trang chính
            modelAndView.setViewName("home"); // Tên view chính
            modelAndView.addObject("message", "Đăng nhập thành công!");
        } else {
            // Nếu đăng nhập thất bại
            modelAndView.setViewName("loginError"); // Tên view lỗi đăng nhập
            modelAndView.addObject("message", "Đăng nhập thất bại! " + response.getMessage());
        }
        return modelAndView;
    }
}
