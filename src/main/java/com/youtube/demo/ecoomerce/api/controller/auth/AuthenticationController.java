package com.youtube.demo.ecoomerce.api.controller.auth;


import com.youtube.demo.ecoomerce.api.model.LoginBody;
import com.youtube.demo.ecoomerce.api.model.LoginResponse;
import com.youtube.demo.ecoomerce.api.model.RegistrationBody;
import com.youtube.demo.ecoomerce.exception.userAlreadyExistsException;
import com.youtube.demo.ecoomerce.model.LocalUser;
import com.youtube.demo.ecoomerce.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

        try {


            userService.registerUser(registrationBody);

            return ResponseEntity.ok().build();
        } catch (

                userAlreadyExistsException ex)
        {

            System.out.println(ex);
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }





    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
        System.out.println("username " + loginBody.getUsername() );
        System.out.println("password " + loginBody.getPassword());
        String jwt = userService.loginUser(loginBody);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            LoginResponse response = new LoginResponse();
            response.setJwt(jwt);
            return ResponseEntity.ok(response);
        }
    }

    @GetMapping("/me")
    public LocalUser getloggedInUserProfile(@AuthenticationPrincipal LocalUser user)
    {
        return user;
    }
}
