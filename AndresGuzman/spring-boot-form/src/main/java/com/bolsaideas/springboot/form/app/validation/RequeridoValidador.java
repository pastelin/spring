package com.bolsaideas.springboot.form.app.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.StringUtils;

public class RequeridoValidador implements ConstraintValidator<Requerido, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		//return ( !(value == null || value.isEmpty() || value.isBlank()) );
		return ( !(value == null || !StringUtils.hasText(value) ) );
	}

}
