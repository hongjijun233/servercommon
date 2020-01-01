package com.lianghongji.controller.validator;


import com.lianghongji.constant.PatternString;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 手机检验实现
 *
 * @author liang .hongji
 */
public class PhoneValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone phone) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s.matches(PatternString.MOBILE)){
            return true;
        }
        return false;
    }
}
