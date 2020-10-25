package org.springframework.samples.petclinic.trainer;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;


/*
* @spacexjedi
*/

@Component
public class AnimalTypeFormatter implements Formatter<AnimalType> {

	 private final AnimalRepository animals;


    @Autowired
    public AnimalTypeFormatter(AnimalRepository animals) {
        this.animals = animals;
    }

    @Override
    public String print(AnimalType animalType, Locale locale) {
        return animalType.getName();
    }

    @Override
    public AnimalType parse(String text, Locale locale) throws ParseException {
        Collection<AnimalType> findAnimalTypes = this.animals.findAnimalTypes();
        for (AnimalType type : findAnimalTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        }
        throw new ParseException("type not found: " + text, 0);
    }

}