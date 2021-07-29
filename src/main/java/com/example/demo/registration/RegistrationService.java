package com.example.demo.registration;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserService;
import com.example.demo.appuser.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidation emailValidation;

    String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidation.test(request.getEmail());

        if(!isValidEmail) {
            throw new IllegalStateException("email not valid");
        }

        return appUserService.signUpUser(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                request.getHomeAddress(),
                UserRole.USER));

    }

}
