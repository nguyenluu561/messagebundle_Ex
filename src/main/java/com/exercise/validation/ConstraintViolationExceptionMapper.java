package com.exercise.validation;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.metadata.ConstraintDescriptor;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		String responseBody = getMessage(exception);
		return Response.status(Response.Status.BAD_REQUEST).entity(responseBody).build();
	}

	private String getMessage(ConstraintViolationException exception) {
		ConstraintViolation<?> constraintViolation = getConstraintViolation(exception);
		String beanErrorCode = getBeanErrorMessage(constraintViolation);
		return beanErrorCode;
	}

	private String getBeanErrorMessage(ConstraintViolation<?> constraintViolation) {
		String violatedType = getViolatedType(constraintViolation);
		switch (violatedType) {
		case "Size":
			return "PROJECT001: TRAINING_ERROR_EMAIL Size is minimun 7 characters";
		case "Email": 
			return "PROJECT001: TRAINING_ERROR_EMAIL Wrong format";
		default:
			return "DEFAULT";
		}
	}

	private String getViolatedType(ConstraintViolation<?> constraintViolation) {
		ConstraintDescriptor<?> constraintDescriptor = constraintViolation.getConstraintDescriptor();
		return constraintDescriptor.getAnnotation().annotationType().getSimpleName();
	}

	private ConstraintViolation<?> getConstraintViolation(ConstraintViolationException exception) {
		return exception.getConstraintViolations().iterator().next();
	}

	
}
