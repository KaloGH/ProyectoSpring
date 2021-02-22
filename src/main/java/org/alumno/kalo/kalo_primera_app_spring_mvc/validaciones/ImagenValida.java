package org.alumno.kalo.kalo_primera_app_spring_mvc.validaciones;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {ValidadorImagenes.class})
public @interface ImagenValida {
	String message() default "{imagenValida.mensajePorDefecto}";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};

}
