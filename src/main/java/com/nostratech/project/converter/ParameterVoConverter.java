package com.nostratech.project.converter;

import com.nostratech.project.persistence.domain.Parameter;
import com.nostratech.project.persistence.vo.ParameterVO;
import com.nostratech.project.util.ExtendedSpringBeanUtil;
import org.springframework.stereotype.Component;

/**
 * Created by yukibuwana on 1/24/17.
 */

@Component
public class ParameterVoConverter extends BaseVoConverter<ParameterVO, ParameterVO, Parameter> {

    @Override
    public ParameterVO transferModelToVO(Parameter model, ParameterVO vo) {
        if (null == vo) vo = new ParameterVO();
        super.transferModelToVO(model, vo);

        ExtendedSpringBeanUtil.copySpecificProperties(model, vo,
                new String[]{"code", "value", "description"});

        return vo;
    }

    @Override
    public Parameter transferVOToModel(ParameterVO vo, Parameter model) {
        if (null == model) model = new Parameter();
        super.transferVOToModel(vo, model);

        ExtendedSpringBeanUtil.copySpecificProperties(vo, model,
                new String[]{"code", "value", "description"});

        return model;
    }
}
