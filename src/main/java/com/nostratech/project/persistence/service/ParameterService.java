package com.nostratech.project.persistence.service;
import com.nostratech.project.converter.IBaseVoConverter;
import com.nostratech.project.converter.ParameterVoConverter;
import com.nostratech.project.persistence.domain.Parameter;
import com.nostratech.project.persistence.repository.BaseRepository;
import com.nostratech.project.persistence.repository.ParameterRepository;
import com.nostratech.project.persistence.vo.ParameterVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Service;

/**
 * Created by yukibuwana on 2/2/17.
 */

@Service
public class ParameterService extends BaseService<Parameter, ParameterVO, ParameterVO> {

    private static final Logger log = LoggerFactory.getLogger(ParameterService.class);

    @Autowired
    ParameterRepository parameterRepository;

    @Autowired
    ParameterVoConverter parameterVoConverter;

    @Override
    protected BaseRepository<Parameter> getJpaRepository() {
        return parameterRepository;
    }

    @Override
    protected JpaSpecificationExecutor<Parameter> getSpecRepository() {
        return parameterRepository;
    }

    @Override
    protected IBaseVoConverter<ParameterVO, ParameterVO, Parameter> getVoConverter() {
        return parameterVoConverter;
    }
}
