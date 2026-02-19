package ee.lilian.veebipood.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PersonService {
    private final String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private final Pattern PATTERN = Pattern.compile(regex);

    public boolean isValid(String email) {
        Matcher matcher = Pattern.matcher(email);
        return matcher.matches();
    }

    public void validate(Person person){
        if (person.getId() != null){
            throw new RuntimeException("Cannot sign up with ID");
        }
        if (person.getEmail() == null) {
            throw new RuntimeException("Cannot sign up with empty email");
        }
        if (person.getPersonalCode() == null){
                throw new RuntimeException("Cannot sign up with empty personal code");
        }
        if (!isValid(person.getEmail())){
            throw new RuntimeException("Invalid email");
    }
}
