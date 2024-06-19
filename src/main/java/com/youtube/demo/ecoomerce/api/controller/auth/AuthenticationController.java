package com.youtube.demo.ecoomerce.api.controller.auth;


import com.youtube.demo.ecoomerce.api.model.RegistrationBody;
import com.youtube.demo.ecoomerce.exception.userAlreadyExistsException;
import com.youtube.demo.ecoomerce.model.LocalUser;
import com.youtube.demo.ecoomerce.service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController  {

    public UserService userService;

    public AuthenticationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity registerUser(@Valid  @RequestBody RegistrationBody registrationBody) throws userAlreadyExistsException {
        System.out.println("req body");
        System.out.println(registrationBody.toString());
        try {
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();
        } catch (
                userAlreadyExistsException ex)
        {

            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }





    }
}
