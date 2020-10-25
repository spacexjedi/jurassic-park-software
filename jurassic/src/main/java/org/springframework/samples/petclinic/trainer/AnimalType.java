package org.springframework.samples.petclinic.trainer;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

/**
 * @spacexjedi
 */
@Entity
@Table(name = "types")
public class AnimalType extends NamedEntity {

}
