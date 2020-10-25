package org.springframework.samples.petclinic.researcher;

import java.util.Collection;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

public interface ResearcherRepository extends Repository<Researcher, Integer> {

	/**
     * Retrieve all <code>Researcher</code>s from the data store.
     *
     * @return a <code>Collection</code> of <code>Vet</code>s
     */
    @Transactional(readOnly = true)
    @Cacheable("researchers")
    Collection<Researcher> findAll() throws DataAccessException;
}