package com.youtube.demo.ecoomerce.service;


import com.youtube.demo.ecoomerce.api.model.LoginBody;
import com.youtube.demo.ecoomerce.api.model.RegistrationBody;
import com.youtube.demo.ecoomerce.exception.userAlreadyExistsException;
import com.youtube.demo.ecoomerce.model.LocalUser;
import com.youtube.demo.ecoomerce.model.dao.LocalUserDAO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private LocalUserDAO localUserDAO;
    private EncryptionService encryptionService;
    private JWTService jwtService;


    public UserService(LocalUserDAO localUserDAO, EncryptionService encryptionService, JWTService jwtService) {
        this.localUserDAO = localUserDAO;
        this.encryptionService = encryptionService;
        this.jwtService = jwtService;
    }


    public LocalUser  registerUser( RegistrationBody registrationBody) throws userAlreadyExistsException
    {


      if(  localUserDAO.findByEmailIgnoreCase(registrationBody.getEmail()).isPresent() || localUserDAO.findByUsernameIgnoreCase(registrationBody.getUsername()).isPresent())
        {
            throw new userAlreadyExistsException();
        }

        LocalUser user= new LocalUser();
        user.setEmail(registrationBody.getEmail());
        user.setUsername(registrationBody.getUsername());
        user.setFirstName(registrationBody.getFirstName());
        user.setLastName(registrationBody.getLastName());
        user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        //   user.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));
        System.out.println(registrationBody.toString());
        return   localUserDAO.save(user);


    }

    public String loginUser(LoginBody loginBody) {
        Optional<LocalUser> opUser = localUserDAO.findByUsernameIgnoreCase(loginBody.getUsername());
        System.out.println("response error" + loginBody.getUsername());
        if (opUser.isPresent()) {

            LocalUser user = opUser.get();

            System.out.println("login body pw "+ loginBody.getPassword());
            System.out.println("user pw " +user.getPassword());

           if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
           // if (encryptionService.verifyPassword(encryptionService.encryptPassword(loginBody.getPassword()), encryptionService.encryptPassword(user.getPassword()))) {

                System.out.println("reached here");
                String token = jwtService.generateJWT(user);
                System.out.println("Token"+token);
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
