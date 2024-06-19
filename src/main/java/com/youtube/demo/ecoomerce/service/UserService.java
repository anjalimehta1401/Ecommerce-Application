package com.youtube.demo.ecoomerce.service;


import com.youtube.demo.ecoomerce.api.model.RegistrationBody;
import com.youtube.demo.ecoomerce.exception.userAlreadyExistsException;
import com.youtube.demo.ecoomerce.model.LocalUser;
import com.youtube.demo.ecoomerce.model.dao.LocalUserDAO;
import org.springframework.stereotype.Service;

import javax.xml.validation.Validator;

@Service
public class UserService {


    private LocalUserDAO localUserDAO;


    public UserService(LocalUserDAO localUserDAO) {
        this.localUserDAO=localUserDAO;

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
        //TODO: Encrypt password!!
        user.setPassword(registrationBody.getPassword());
        System.out.println(registrationBody.toString());
        return   localUserDAO.save(user);


    }
}
