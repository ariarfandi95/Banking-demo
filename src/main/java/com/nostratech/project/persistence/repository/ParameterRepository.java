package com.nostratech.project.persistence.repository;

import com.nostratech.project.persistence.domain.Parameter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by yukibuwana on 1/30/17.
 */

@Repository
public interface ParameterRepository extends BaseRepository<Parameter> {
    Parameter findByCode(String code);
}