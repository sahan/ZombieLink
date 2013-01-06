/**
 * 
 */
package com.lonepulse.zombielink.core.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import com.lonepulse.zombielink.core.annotation.Header;
import com.lonepulse.zombielink.core.annotation.Param;


/**
 * <p>This is a utility class which is used to extract annotations from different 
 * {@link Class}es.</p>
 * 
 * @version 1.1.2
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class AnnotationExtractor {

	/**
	 * <p>Extracts annotations of a given type from a {@link Method} and returns 
	 * them along with their arguments provided as an {@link Object} array.</p>
	 * 
	 * <p>These arguments maybe collected via a dynamic proxy.</p>
	 * 
	 * @param annotationType
	 * 			the {@link Class} of the annotation to extract
	 * 
	 * @param method
	 * 			the {@link Method} on which to look for annotations of the 
	 * 			given type
	 * 
	 * @param args
	 * 			the {@link Object} array of method argument collected via 
	 * 			the {@link Proxy}
	 * <br><br>
	 * @return
	 * 			the list of {@link Annotation}s along with their runtime 
	 * 			parameter values
	 * <br><br>
	 * @since 1.1.1
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Annotation> Map<Object, T> extractWithParameterValues(Class<T> annotationType, Method method, Object[] args) {
		
		Map<Object, T> argumentAndAnnotationList = new HashMap<Object, T>();
		
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		
		int i = 0;
		for(Annotation[] annotationSet: parameterAnnotations) { 
			
			for(Annotation annotation: annotationSet)
				if(annotationType.isAssignableFrom(annotation.getClass()))
					argumentAndAnnotationList.put(args[i++], (T)annotation);
		}
		
		return argumentAndAnnotationList;
	}
	
	/**
	 * <p>Extracts header annotations from a {@link Method} and returns them along with 
	 * their arguments provided as an {@link Object} array. This makes use of 
	 * {@link AnnotationExtractor#extractWithParameterValues(Class, Method, Object[])} 
	 * with the annotation type parameter as {@link Header}.</p>
	 * 
	 * @param method
	 * 			the {@link Method} on which to look for annotations of the given type
	 * 
	 * @param args
	 * 			the {@link Object} array of method argument collected via the {@link Proxy}
	 * <br><br>
	 * @return
	 * 			the list of variable header {@link Param}s along with their runtime 
	 * 			parameter values
	 * <br><br>
	 * @since 1.1.2
	 */
	public static Map<StringBuilder, Header> extractHeaders(Method method, Object[] args) {
		
		Map<StringBuilder, Header> headerParams = new HashMap<StringBuilder, Header>();
		
		Annotation[][] parameterAnnotations = method.getParameterAnnotations();
		
		int i = 0;
		for(Annotation[] annotationSet: parameterAnnotations) { 
			
			for(Annotation annotation: annotationSet) {
				
				if(Header.class.isAssignableFrom(annotation.getClass())) 
					headerParams.put((StringBuilder)args[i], (Header)annotation);
				
				i++;
			}
		}
		
		return headerParams;
	}
}
