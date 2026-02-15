package com.example.jobbble_spring.validators;

import com.example.jobbble_spring.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (user.getPassword() == null || !isStrongPassword(user.getPassword())) {
            errors.rejectValue("password", "weak.password", "Password must be at least 6 characters long and contain both uppercase and lowercase letters.");
        }
    }

    private boolean isStrongPassword(String password) {
        if (password.length() < 6) {
            return false;
        }
        boolean hasUppercase = false;
        boolean hasLowercase = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            }
        }

        return hasUppercase && hasLowercase;
    }
}
