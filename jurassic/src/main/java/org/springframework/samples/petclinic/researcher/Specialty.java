package org.springframework.samples.petclinic.researcher;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;

/*
* @spacexjedi
*/

/*
* Models {@link Researcher}
*/

@Entity
@Table(name = "specialties")
public class Specialty extends NamedEntity implements Serializable {
	
}
