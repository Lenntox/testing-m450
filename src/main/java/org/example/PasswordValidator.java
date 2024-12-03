package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {

    public boolean isPasswordValid(String password) {
        return password != null && password.length() >= 8 && password.length() <= 20 && !password.contains(" ") && !hasNumeric(password) && !hasLowercase(password) && !hasUppercase(password) && !hasSpecial(password);
    }

    public boolean hasNumeric(String password){
        char[] chars = password.toCharArray();
        for (char aChar : chars) {
            if(Character.isDigit(aChar)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasLowercase(String password){
        char[] chars = password.toCharArray();
        for (char aChar : chars) {
            if(Character.isLowerCase(aChar)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasUppercase(String password){
        char[] chars = password.toCharArray();
        for (char aChar : chars) {
            if(Character.isUpperCase(aChar)) {
                return false;
            }
        }
        return true;
    }

    public boolean hasSpecial(String password){
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(password);
        return !hasSpecial.find();
    }
}