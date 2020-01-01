package com.lianghongji.controller.validator;


import com.lianghongji.constant.PatternString;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 邮箱检验实现
 *
 * @author  liang.hongji
 */
public class EmailValidator implements ConstraintValidator<Email, String> {
    @Override
    public void initialize(Email email) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s.matches(PatternString.EMAIL)){
            return true;
        }
        return false;
    }
}
