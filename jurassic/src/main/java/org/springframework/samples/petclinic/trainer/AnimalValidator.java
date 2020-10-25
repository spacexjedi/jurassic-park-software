package org.springframework.samples.petclinic.trainer;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/*
* @spacexjedi
 */
public class AnimalValidator implements Validator {

    private static final String REQUIRED = "required";

    @Override
    public void validate(Object obj, Errors errors) {
        Animal animal = (Animal) obj;
        String name = animal.getName();
        // name validation
        if (!StringUtils.hasLength(name)) {
            errors.rejectValue("name", REQUIRED, REQUIRED);
        }

        // type validation
        if (animal.isNew() && animal.getType() == null) {
            errors.rejectValue("type", REQUIRED, REQUIRED);
        }

        // birth date validation
        if (animal.getBirthDate() == null) {
            errors.rejectValue("birthDate", REQUIRED, REQUIRED);
        }
    }

    /**
     * This Validator validates *just* Animal instances
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Animal.class.isAssignableFrom(clazz);
    }


}
