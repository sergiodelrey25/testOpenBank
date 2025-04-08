package com.capgemini.test.code.domain.core.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import jakarta.persistence.*;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class AbstractEntity<E> {
	private static final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	//Long incremental que representa el id se genera de forma incremental
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	@JsonIgnore
	@SuppressWarnings("unchecked")
	public Set<ConstraintViolation<E>> getErrors() {
		return validator.validate((E)this);
	}

	@JsonIgnore
	@Transient
	public Map<String, String> getErrorsFields() {
		Set<ConstraintViolation<E>> lst = getErrors();
		if (lst.isEmpty())
			return null;
		Map<String, String> errors = new HashMap<>();
		lst.stream().sorted((a,b)->(a.getPropertyPath().toString() + ":" + a.getMessage()).compareTo(b.getPropertyPath().toString() + ":" + b.getMessage()))
			.forEach(item -> errors.put(item.getPropertyPath().toString(), 
					(errors.containsKey(item.getPropertyPath().toString()) ? errors.get(item.getPropertyPath().toString()) + ", " : "") 
					+ item.getMessage()));
		return errors;
	}

	@JsonIgnore
	@Transient
	public String getErrorsMessage() {
		Set<ConstraintViolation<E>> lst = getErrors();
		if (lst.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder("ERRORES:");
		getErrorsFields().forEach((fld, err) -> sb.append(" " + fld + ": " + err + "."));
		return sb.toString();
	}

	@Transient
	@JsonIgnore
	public boolean isValid() {
		return getErrors().size() == 0;
	}

	@Transient
	@JsonIgnore
	public boolean isInvalid() {
		return !isValid();
	}

}