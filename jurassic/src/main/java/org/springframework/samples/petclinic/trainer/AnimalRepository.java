package org.springframework.samples.petclinic.trainer;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
* @spacexjedi
*/

public interface AnimalRepository extends Repository<Animal, Integer> {

	 /**
     * Retrieve all {@link AnimalType}s from the data store.
     * @return a Collection of {@link AnimalType}s.
     */

    @Query("SELECT ptype FROM AnimalType ptype ORDER BY ptype.name")
    @Transactional(readOnly = true)
    List<AnimalType> findAnimalTypes();

    /**
     * Retrieve a {@link Animal} from the data store by id.
     * @param id the id to search for
     * @return the {@link Animal} if found
     */
    @Transactional(readOnly = true)
    Animal findById(Integer id);

    /**
     * Save a {@link Animal} to the data store, either inserting or updating it.
     * @param animal the {@link Animal} to save
     */
    void save(Animal animal);
}