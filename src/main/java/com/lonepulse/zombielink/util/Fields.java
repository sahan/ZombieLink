package com.lonepulse.zombielink.util;

/*
 * #%L
 * ZombieLink
 * %%
 * Copyright (C) 2013 Lonepulse
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import static com.lonepulse.zombielink.util.Assert.assertNotEmpty;
import static com.lonepulse.zombielink.util.Assert.assertNotNull;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>Fluent filtering for a collection of {@link Field}s.</p> 
 * 
 * <p><b>Note</b> that two {@link Field}s are determined to be equal of and only if they were declared 
 * in the same {@link Class}, using the same name and type.</p>
 * 
 * @version 1.1.0
 * <br><br>
 * @since 1.2.4
 * <br><br>
 * @author <a href="mailto:sahan@lonepulse.com">Lahiru Sahan Jayasinghe</a>
 */
public final class Fields {

	
	private Collection<Field> fields = null;
	
	
	/**
	 * <p>Creates a new instance of {@link Fields} from the given <b>object</b> by extracting all its 
	 * member {@link Field}s.</p> 
	 * 
	 * <p>See {@link Object#getClass()} and {@link Class#getDeclaredFields()}.</p>
	 *
	 * @param target
	 * 			the object whose fields are to extracted
	 * <br><br>
	 * @return a new instance of {@link Fields} for the {@link Field}s on the given object
	 * <br><br>
	 * @since 1.2.4
	 */
	public static final Fields in(Object target) {
		
		return new Fields(target.getClass().getDeclaredFields());
	}
	
	/**
	 * <p>Creates a new instance of {@link Fields} from the given <b>{@link Class}</b> by extracting 
	 * all its member {@link Field}s.</p> 
	 * 
	 * <p>See {@link Class#getDeclaredFields()}.</p>
	 *
	 * @param target
	 * 			the {@link Class} whose fields are to extracted
	 * 
	 * @return a new instance of {@link Fields} for the {@link Field}s on the given {@link Class}
	 * 
	 * @since 1.2.4
	 */
	public static final Fields in(Class<? extends Object> target) {
		
		return new Fields(target.getDeclaredFields());
	}
	
	private Fields(Field[] fields) {
		
		assertNotNull(fields, Field[].class);
		this.fields = Collections.unmodifiableList(Arrays.asList(fields));
	}
	
	private Fields(Collection<Field> fields) {
		
		assertNotNull(fields, Collection.class);
		this.fields = Collections.unmodifiableCollection(fields);
	}
	
	/**
	 * <p>Filters the {@link Field}s which are annotated with the given annotation and returns a new 
	 * instance of {@link Fields} that wrap the filtered collection.</p>
	 * 
	 * @param annotation
	 * 			the {@link Field}s annotated with this type will be filtered
	 * <br><br>
	 * @return a <b>new instance</b> of {@link Fields} which wraps the filtered {@link Field}s
	 * <br><br>
	 * @since 1.2.4
	 */
	public Fields annotatedWith(Class<? extends Annotation> annotation) {
		
		assertNotNull(annotation, Class.class);
		
		List<Field> filteredFields = new ArrayList<Field>();
		
		for (Field field : fields) {
			
			if(field.isAnnotationPresent(annotation)) {
				
				filteredFields.add(field);
			}
		}
		
		return new Fields(filteredFields);
	}
	
	/**
	 * <p>Filters the {@link Field}s which are annotated with <b>all</b> the given annotations and 
	 * returns a new instance of {@link Fields} that wrap the filtered collection.</p>
	 * 
	 * @param annotation
	 * 			the {@link Field}s annotated with <b>all</b> these types will be filtered
	 * <br><br>
	 * @return a <b>new instance</b> of {@link Fields} which wraps the filtered {@link Field}s
	 * <br><br>
	 * @since 1.2.4
	 */
	public Fields annotatedWithAll(Class<? extends Annotation>... annotations) {
		
		assertNotNull(annotations, Class[].class);
		
		List<Field> filteredFields = new ArrayList<Field>();
		
		boolean hasAllAnnotations;
		
		for (Field field : fields) {
			
			hasAllAnnotations = true;
			
			for (Class<? extends Annotation> annotation : annotations) {
			
				if(!field.isAnnotationPresent(annotation)) {
					
					hasAllAnnotations = false;
					break;
				}
			}
			
			if(hasAllAnnotations) {
				
				filteredFields.add(field);
			}
		}
		
		return new Fields(filteredFields);
	}
	
	/**
	 * <p>Filters the {@link Field}s which are annotated with <b>any</b> of the given annotations and 
	 * returns a new instance of {@link Fields} that wrap the filtered collection.</p>
	 * 
	 * @param annotation
	 * 			the {@link Field}s annotated with <b>any</b> of these types will be filtered
	 * <br><br>
	 * @return a <b>new instance</b> of {@link Fields} which wraps the filtered {@link Field}s
	 * <br><br>
	 * @since 1.2.4
	 */
	public Fields annotatedWithAny(Class<? extends Annotation>... annotations) {
		
		assertNotNull(annotations, Class[].class);
		
		List<Field> filteredFields = new ArrayList<Field>();
		
		for (Field field : fields) {
			
			for (Class<? extends Annotation> annotation : annotations) {
				
				if(field.isAnnotationPresent(annotation)) {
					
					filteredFields.add(field);
					break;
				}
			}
		}
		
		return new Fields(filteredFields);
	}
	
	/**
	 * <p>Filters the {@link Field}s whose name equals (case-sensitive) the given name and returns 
	 * a new instance of {@link Fields} that wrap the filtered collection.</p> 
	 *
	 * @param fieldName
	 * 			the {@link Field}s having this name will be filtered
	 * <br><br>
	 * @return a <b>new instance</b> of {@link Fields} which wraps the filtered {@link Field}s
	 * <br><br>
	 * @since 1.2.4
	 */
	public Fields nameEquals(String fieldName) {
		
		assertNotEmpty(fieldName);
		
		List<Field> filteredFields = new ArrayList<Field>();
		
		for (Field field : fields) {
			
			if(field.getName().equals(fieldName)) {
				
				filteredFields.add(field);
			}
		}
		
		return new Fields(filteredFields);
	}
	
	/**
	 * <p>Filters the {@link Field}s whose <b>case insensitive</b> name equals the given name and 
	 * returns a new instance of {@link Fields} that wrap the filtered collection. 
	 *
	 * @param fieldName
	 * 			the {@link Field}s having this case insensitive name will be filtered
	 * 
	 * @return a <b>new instance</b> of {@link Fields} which wraps the filtered {@link Field}s
	 * 
	 * @since 1.2.4
	 */
	public Fields nameEqualsIgnoreCase(String fieldName) {
		
		assertNotEmpty(fieldName);
		
		List<Field> filteredFields = new ArrayList<Field>();
		
		for (Field field : fields) {
			
			if(field.getName().equalsIgnoreCase(fieldName)) {
				
				filteredFields.add(field);
			}
		}
		
		return new Fields(filteredFields);
	}
	
	/**
	 * <p>Finds the <b>difference</b> between this instance and a supplied instance. Returns a new 
	 * instance of {@link Fields} with only those {@link Field}s which unique to this instance.</p> 
	 * 
	 * <p><pre>
	 * Difference can be expressed as, 
	 * <code>
	 * A - B = { x &isin; A and x &notin; B }
	 * </code> 
	 * where,
	 * 
	 * A = this Fields instance
	 * B = supplied Fields instance
	 * x = any field from the resulting Fields instance 
	 * </pre></p>
	 *
	 * @param fields
	 * 			the instance of {@link Fields} whose common items are subtracted from this instance
	 * <br><br>
	 * @return a new instance of {@link Fields} which wraps only the only those {@link Field}s which 
	 * 		   are unique to this instance
	 * <br><br>
	 * @since 1.2.4
	 */
	public Fields difference(Fields fields) {
		
		assertNotNull(fields);
		
		Set<Field> view = new HashSet<Field>(this.fields);
		view.removeAll(new HashSet<Field>(fields.list()));
		
		return new Fields(view);
	}
	
	/**
	 * <p>Finds the <b>union</b> between this instance and a supplied instance. Reeturns a new 
	 * instance of {@link Fields} which contains a <b>set of all</b> the {@link Field}s which in 
	 * both this instance and the supplied instance.</p>
	 * 
	 * <p><pre>
	 * Union can be expressed as, 
	 * <code>
	 * A &cup; B = { x : x &isin; A or x &isin; B }
	 * </code> 
	 * where,
	 * 
	 * A = this Fields instance
	 * B = supplied Fields instance
	 * x = any field from the resulting Fields instance 
	 * </pre></p>
	 * 
	 * @param fields
	 * 			the instance of {@link Fields} whose {@link Field}s are added to this instance
	 * <br><br>
	 * @return a new instance of {@link Fields} which wraps all the unique {@link Field}s in this 
	 * 		   instance and the given instance
	 * <br><br>
	 * @since 1.2.4
	 */
	public Fields union(Fields fields) {
		
		assertNotNull(fields);
		
		Set<Field> view = new HashSet<Field>(this.fields);
		view.addAll(new HashSet<Field>(fields.list()));
		
		return new Fields(view);
	}
	
	/**
	 * <p>Finds the <b>intersection</b> between this instance and a supplied instance. Returns a new 
	 * instance of {@link Fields} which contains all the {@link Field}s <b>common</b> to both instances.</b>
	 * 
	 * <p><pre>
	 * Intersection can be expressed as, 
	 * <code>
	 * A &cap; B = { x : x &isin; A and x &isin; B }
	 * </code> 
	 * where,
	 * 
	 * A = this Fields instance
	 * B = supplied Fields instance
	 * x = any field from the resulting Fields instance 
	 * </pre></p>
	 * 
	 * @param fields
	 * 			the instance of {@link Fields} whose common items are discovered
	 * <br><br>
	 * @return a new instance of {@link Fields} which wraps only the only those {@link Field}s which 
	 * 		   are common between this instance and the passed instance
	 * <br><br>
	 * @since 1.2.4
	 */
	public Fields intersection(Fields fields) {
		
		assertNotNull(fields);
		
		Set<Field> view = new HashSet<Field>(this.fields);
		view.retainAll(new HashSet<Field>(fields.list()));
		
		return new Fields(view);
	}
	
	/**
	 * <p>Retrieves all the {@link Field}s which are wrapped by this instance of {@link Fields} 
	 * as an instance of {@link ArrayList}.</p>
	 *
	 * @return the fields which are wrapped by this instance of {@link Fields}
	 */
	public List<Field> list() {
		
		return new ArrayList<Field>(fields);
	}
}
