package org.springframework.samples.petclinic.trainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.samples.petclinic.model.Person;

/*
* @spacexjedi
*/

@Entity
@Table(name = "trainer")
public class Trainer extends Person {
	@Column(name = "address")
	@NotEmpty
	private String address;

@Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Pet> pets;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    // animals

    protected Set<Animal> getAnimalsInternal() {
        if (this.animals == null) {
            this.animals = new HashSet<>();
        }
        return this.animals;
    }

    protected void setPetsInternal(Set<Animal> animals) {
        this.animals = animals;
    }

    public List<Animal> getAnimals() {
        List<Animal> sortedAnimals = new ArrayList<>(getAnimalsInternal());
        PropertyComparator.sort(sortedAnimals,
                new MutableSortDefinition("name", true, true));
        return Collections.unmodifiableList(sortedAnimals);
    }

    public void addAnimal(Animal animal) {
        if (animal.isNew()) {
            getAnimalsInternal().add(animal);
        }
        animal.setAnimals(this);
    }

    /**
     * 
     *
     * @param name to test
     * @return true if name is already in use
     */
    public Animal getAnimal(String name) {
        return getAnimal(name, false);
    }

    /**
     * 
     *
     * @param name to test
     * @return true if name is already in use
     */
    public Animal getAnimal(String name, boolean ignoreNew) {
        name = name.toLowerCase();
        for (Animal animal : getAnimalsInternal()) {
            if (!ignoreNew || !animal.isNew()) {
                String compName = animal.getName();
                compName = compName.toLowerCase();
                if (compName.equals(name)) {
                    return animal;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)

                .append("id", this.getId()).append("new", this.isNew())
                .append("lastName", this.getLastName())
                .append("firstName", this.getFirstName()).append("address", this.address)
                .append("city", this.city).append("telephone", this.telephone).toString();
    }




}