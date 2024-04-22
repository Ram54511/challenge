package com.code.challenge.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class AgeNotFutureValidator implements ConstraintValidator<AgeNotFuture, LocalDate> {

    @Override
    public void initialize(AgeNotFuture constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        return value == null || !value.isAfter(LocalDate.now());
    }
}