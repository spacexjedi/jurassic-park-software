package org.springframework.samples.petclinic.trainer;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


/*
* @spacexjedi
*/

public interface TrainerRepository extends Repository<Trainer, Integer> {

	 /**
     * Retrieve {@link Trainer}s from the data store by last name, returning all trainers
     * whose last name <i>starts</i> with the given name.
     * @param lastName Value to search for
     * @return a Collection of matching {@link Trainer}s (or an empty Collection if none
     * found)
     */

     @Query("SELECT DISTINCT trainer FROM Trainer trainer left join fetch trainer.animals WHERE trainer.lastName LIKE :lastName%")
     @Transactional(readOnly = true)
     Collection<Trainer> findByLastName(@Param("lastName") String LastName);

        /**
     * Retrieve an {@link Trainer} from the data store by id.
     * @param id the id to search for
     * @return the {@link Trainer} if found
     */
    @Query("SELECT trainer FROM Trainer trainer left join fetch trainer.animals WHERE trainer.id =:id")
    @Transactional(readOnly = true)
    Trainer findById(@Param("id") Integer id);

    /**
     * Save an {@link Trainer} to the data store, either inserting or updating it.
     * @param trainer the {@link Trainer} to save
     */
    void save(Trainer trainer);
}