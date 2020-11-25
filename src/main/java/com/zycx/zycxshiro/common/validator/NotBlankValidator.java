package com.zycx.zycxshiro.common.validator;


import com.zycx.zycxshiro.common.annotation.NotBlank;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author : wl
 * @version : 1

 */
public class NotBlankValidator implements ConstraintValidator<NotBlank,String> {
    @Override
    public void initialize(NotBlank constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return StringUtils.isNotBlank(value);
    }
}
