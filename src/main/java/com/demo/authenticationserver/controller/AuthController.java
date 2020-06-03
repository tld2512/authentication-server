package com.demo.authenticationserver.controller;

import com.demo.authenticationserver.model.Otp;
import com.demo.authenticationserver.model.User;
import com.demo.authenticationserver.repository.OtpRepository;
import com.demo.authenticationserver.service.UserService;
import com.demo.authenticationserver.utils.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private OtpRepository otpRepository;

    @PostMapping("/user/add")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PostMapping("/user/auth")
    public void auth(@RequestBody User user) throws IOException, MessagingException {
        if (userService.auth(user)) {
            User myUser = userService.findUserByUsername(user.getUsername()).get();
            MailUtil.sendmail(myUser.getEmail(), otpRepository.findCodeByUsername(user.getUsername()).get());
        }
    }

    @PostMapping("/otp/check")
    public void check(@RequestBody Otp otp, HttpServletResponse response) throws IOException, MessagingException {
        if (userService.check(otp)) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}
