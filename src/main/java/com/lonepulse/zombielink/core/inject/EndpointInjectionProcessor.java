/**
 * 
 */
package com.lonepulse.zombielink.core.inject;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

import com.lonepulse.zombielink.core.annotation.Bite;

/**
 * <p>This is the compile time annotation processor which scans the classpath for 
 * entities annotated with {@link Bite} and injects the appropriate proxy.
 * 
 * @version 1.1.1
 * <br><br>
 * @author <a href="mailto:lahiru@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
@SupportedAnnotationTypes("com.lonepulse.zombielink.core.inject.Zombie")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class EndpointInjectionProcessor extends AbstractProcessor {

	/**
	 * @see javax.annotation.processing.AbstractProcessor#process(java.util.Set, javax.annotation.processing.RoundEnvironment)
	 */
	@Override
	@SuppressWarnings("unused")
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {

		for (Element element : roundEnvironment.getElementsAnnotatedWith(Bite.class)) {
			
			if(element.getKind() == ElementKind.FIELD) { //property injection requested

				String endpointName;
				String fieldName;
				
				TypeMirror typeMirror = element.asType();
				
				if(typeMirror.getKind() == TypeKind.DECLARED) {
					
					endpointName = ((DeclaredType)typeMirror).toString();
					fieldName = element.getSimpleName().toString();
					
					//TODO Validate injection!! ??
					
				}
			}
			if(element.getKind() == ElementKind.CONSTRUCTOR) { //constructor injection requested
				
				
			}
		}
		
		return true; //mark this annotation as "processed"
	}
}
