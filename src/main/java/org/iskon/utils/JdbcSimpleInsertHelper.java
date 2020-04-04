package org.iskon.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Component;

@Component
public class JdbcSimpleInsertHelper {

	HashMap<String, List<FieldHolder>> fieldsCache = new HashMap<>();

	static final Lock lock = new ReentrantLock();

	public JdbcSimpleInsertHelper prepareObject(Object obj) {
		Class<? extends Object> objClass = obj.getClass();
		String objClassStr = objClass.toString();

		List<FieldHolder> fields = fieldsCache.getOrDefault(objClassStr, null);
		if (fields == null) {
			lock.lock();
			try {
				fields = fieldsCache.getOrDefault(objClassStr, null);
				if (fields == null) {
					Field[] classFields = objClass.getDeclaredFields();
					Field[] superFields = objClass.getSuperclass().getDeclaredFields();
					List<FieldHolder> tempFields = new ArrayList<FieldHolder>();

					for (Field field : classFields) {
						if(field.getName() != "id")
							tempFields.add(new FieldHolder(field.getName(), field));
					}

					for (Field field : superFields) {
						tempFields.add(new FieldHolder(field.getName(), field));

					}
					fieldsCache.putIfAbsent(objClassStr, tempFields);
				}
			} finally {
				lock.unlock();
			}
		}

		return this;
	}

	public List<String> getColumns(Object obj)
	{
		Class<? extends Object> objClass = obj.getClass();
		String objClassStr = objClass.toString();
		
		List<FieldHolder> fields = fieldsCache.getOrDefault(objClassStr, null);
		
		if(fields == null)
			throw new RuntimeException("Fields not Parsed. May be the prepare was not called.");
		
		List<String> columns = new ArrayList<String>();
		
		for (FieldHolder fieldHolder : fields) {
			columns.add(fieldHolder.getName().toLowerCase());
		}
		
		return columns;
	}
	
	public Map<String,Object> getDataMap(Object obj)
	{
		Class<? extends Object> objClass = obj.getClass();
		String objClassStr = objClass.toString();
		
		List<FieldHolder> fields = fieldsCache.getOrDefault(objClassStr, null);
		
		if(fields == null)
			throw new RuntimeException("Fields not Parsed. May be the prepare was not called.");
		
		Map<String,Object> fieldDataMap = new HashMap<>();
		
		for (FieldHolder fieldHolder : fields) {
			try {
				fieldHolder.getField().setAccessible(true);
				Object value = fieldHolder.getField().get(obj);
				String name = fieldHolder.getName().toLowerCase();
				fieldDataMap.put(name, value);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return fieldDataMap;
	}
}
