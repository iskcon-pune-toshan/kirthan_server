package org.iskon.utils;

import java.lang.reflect.Field;

public class FieldHolder {
	
	public FieldHolder(String name,Field field)
	{
		this.name = name;
		this.field = field;
	}
	
	private String name;
	private Field field;

	String getName() {
		return name;
	}

	Field getField() {
		return field;
	}

}
