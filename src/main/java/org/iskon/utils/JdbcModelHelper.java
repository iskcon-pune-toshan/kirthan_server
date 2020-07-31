package org.iskon.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.iskon.models.InsertNotAllowed;
import org.iskon.models.KeyField;
import org.iskon.models.UpdateAllowed;
import org.springframework.stereotype.Component;

@Component
public class JdbcModelHelper {

	HashMap<String, List<FieldHolder>> fieldsCache = new HashMap<>();
	HashMap<String, List<FieldHolder>> keyfieldsCache = new HashMap<>();
	HashMap<String, List<FieldHolder>> updatefieldsCache = new HashMap<>();

	static final Lock lock = new ReentrantLock();

	public JdbcModelHelper prepareObject(Class<? extends Object> type) {
		//Class<? extends Object> objClass = obj.getClass();
		String objClassStr = type.toString();

		List<FieldHolder> fields = fieldsCache.getOrDefault(objClassStr, null);
		if (fields == null) {
			lock.lock();
			try {
				fields = fieldsCache.getOrDefault(objClassStr, null);
				if (fields == null) {
					Field[] classFields = type.getDeclaredFields();
					Field[] superFields = type.getSuperclass().getDeclaredFields();
					
					List<FieldHolder> tempFields = new ArrayList<FieldHolder>();
					List<FieldHolder> keyFields = new ArrayList<FieldHolder>();
					List<FieldHolder> updateFields = new ArrayList<FieldHolder>();
					
					parseFields(classFields, tempFields, keyFields, updateFields);
					parseFields(superFields, tempFields, keyFields, updateFields);
					
					fieldsCache.putIfAbsent(objClassStr, tempFields);
					keyfieldsCache.putIfAbsent(objClassStr, keyFields);
					updatefieldsCache.putIfAbsent(objClassStr, updateFields);
					}
				
			} finally {
				lock.unlock();
			}
		}

		return this;
	}

	private void parseFields(Field[] classFields, List<FieldHolder> tempFields, List<FieldHolder> keyFields,
			List<FieldHolder> updateFields) {
		for (Field field : classFields) {
			
			FieldHolder fieldHolder = new FieldHolder(field.getName(), field);
			
			if(field.getAnnotation(KeyField.class) != null)
			{
				keyFields.add(fieldHolder);
			}
			else
				tempFields.add(fieldHolder);
			
			if(field.getAnnotation(UpdateAllowed.class) != null)
				updateFields.add(fieldHolder);
		}
	}

	public List<String> getColumns(Object obj, FieldCacheType opType)
	{
		Class<? extends Object> objClass = obj.getClass();
		String objClassStr = objClass.toString();
		
		Field[] classFields = objClass.getDeclaredFields();
		
		List<FieldHolder> fields = getFieldsCache(opType, objClassStr);
		if(fields == null)
			throw new RuntimeException("Fields not Parsed. May be the prepare was not called.");
		
		List<String> columns = new ArrayList<String>();
		
		boolean insertflag = false;
		for (FieldHolder fieldHolder : fields) {
			insertflag = false;
			for (Field field : classFields) {
				if(field.getAnnotation(InsertNotAllowed.class) != null && field.getName().equalsIgnoreCase(fieldHolder.getName().toLowerCase()) ) {
					insertflag = true;
					break;
				}
			}
			if (insertflag == false) {
				columns.add(fieldHolder.getName().toLowerCase());
			}
		}
		
		return columns;
	}

	private List<FieldHolder> getFieldsCache(FieldCacheType opType, String objClassStr) {
		List<FieldHolder> fields = null;
		if(opType == FieldCacheType.ForInsert)
			fields = fieldsCache.getOrDefault(objClassStr, null);
		if(opType == FieldCacheType.ForUpdate)
			fields = updatefieldsCache.getOrDefault(objClassStr, null);
		if(opType == FieldCacheType.Keys)
			fields = keyfieldsCache.getOrDefault(objClassStr, null);
		return fields;
	}
	
	public Map<String,Object> getDataMap(Object obj, FieldCacheType opType)
	{
		Class<? extends Object> objClass = obj.getClass();
		String objClassStr = objClass.toString();
		
		List<FieldHolder> fields = getFieldsCache(opType, objClassStr);
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
