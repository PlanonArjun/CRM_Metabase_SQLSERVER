package crm.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import crm.enums.CategoryType;



/**
 * Framework Annotation is user built com.vt.annotation which is annotated on
 * top of test methods to log the author details and category details to the
 * extent report.
 * 
 * Runtime retention value indicate that this com.vt.annotation will be
 * available at run time for reflection operations.
 */

@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface FrameworkAnnotation {

	public String[] author();

	public CategoryType[] category();

}
