package com.nostratech.project.controller;


import com.nostratech.project.persistence.domain.Parameter;
import com.nostratech.project.persistence.service.BaseService;
import com.nostratech.project.persistence.service.ParameterService;
import com.nostratech.project.persistence.vo.ParameterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yukibuwana on 2/2/17.
 */

@RestController
@RequestMapping("/api/v1/parameter")
public class ParameterController extends BaseController<Parameter, ParameterVO, ParameterVO> {

    @Autowired
    ParameterService parameterService;

    @Override
    protected BaseService<Parameter, ParameterVO, ParameterVO> getDomainService() {
        return parameterService;
    }
}
