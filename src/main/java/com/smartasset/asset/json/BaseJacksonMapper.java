package com.smartasset.asset.json;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.JavaType;

import com.smartasset.asset.exception.JsonException;

/**
 * Responsible for converting JSON into Asset and vice-versa. 
 * This implementation uses Jackson which works faster on large amounts of data.
 * Check class GsonMapper for small amount o data.
 * 
 * This guys did a good comparison between JSON classes
 * http://blog.takipi.com/the-ultimate-json-library-json-simple-vs-gson-vs-jackson-vs-json/
 * 
 * @author Marcos Costa
 *
 * @param <T>
 */
public class BaseJacksonMapper<T> implements JsonMapper<T> {

	private Class<T> referenceClass;
	protected ObjectMapper mapper;
	
	public BaseJacksonMapper(Class<T> referenceClass){
		this.referenceClass = referenceClass;
		this.mapper = initialize();
		setMapperConfiguration();
	}
	
	private ObjectMapper initialize() {
		return new ObjectMapper();
	}
	
	protected void setMapperConfiguration() {
		mapper.configure(Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
		mapper.configure(Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	public List<T> readValue(String content) throws JsonException {
		List <T> result = new ArrayList<T>(0);
		if (content == null) return result;
		try {
			JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, referenceClass);
			result = mapper.readValue(content, type);
		} catch (Exception e) {
			throw new JsonException("Could not parse Json with Jackson", e);
		}
		
		return result;
	}

	@Override
	public MapperType getType() {
		return MapperType.JACKSON;
	}
}
