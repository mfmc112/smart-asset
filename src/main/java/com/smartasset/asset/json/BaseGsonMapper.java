package com.smartasset.asset.json;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smartasset.asset.exception.JsonException;
import com.smartasset.asset.json.util.Element;
import com.smartasset.asset.json.util.ElementList;

/**
 * Responsible for converting JSON into Asset and vice-versa. 
 * This implementation uses Google Gson which is fast for small amount of data.
 * Check class JacksonMapper for larger amount o data
 * 
 * This guys did a good comparison between JSON classes
 * http://blog.takipi.com/the-ultimate-json-library-json-simple-vs-gson-vs-jackson-vs-json/
 * 
 * @author Marcos Costa
 *
 * @param <T>
 */
public class BaseGsonMapper<T> implements JsonMapper<T> {
	
	private Class<T> elementClass;
	protected Gson mapper;
	
	public BaseGsonMapper(Class<T> elementClass){
		this.elementClass = elementClass;
		initialize();
		setMapperConfiguration();
	}
	
	private void initialize(){
		GsonBuilder builder  = new GsonBuilder();
		this.mapper = builder.create();
	}
	
	protected void setMapperConfiguration(){
		// extend this class and Override this method configure as needed
	}

	@Override
	public List<T> readValue(String content) throws JsonException {
		if (content == null) return null;
		List<T> list = new ArrayList<T>();
		try{
			if (isArray(content)){
				list = fromArrayToJSON(content, elementClass);
			} else{
				list.add(fromObjectToJSON(content, elementClass));
			}
		}catch(Exception e){
			throw new JsonException("Could not parse Json with Gson", e);
		}
		return list;
	}
	
	@Override
	public T readSingleValue(String content) throws JsonException {
		if (content == null) return null;
		try{
			if (isArray(content)){
				throw new JsonException("The content is an array");
			}
			return fromObjectToJSON(content, elementClass);
		}catch(JsonException e){
			throw e;
		}catch(Exception e){
			throw new JsonException("Could not parse Json with Gson", e);
		}
	}
	
	/*
	 * Convert a single JSON to the Asset passed as generic.
	 * 
	 * @param json
	 * @param elementClass
	 * @return T
	 */
	private T fromObjectToJSON(String json, Class<T> elementClass){
		T asset = mapper.fromJson(json, new Element<T>(elementClass));
		return asset;
	}

	/*
	 * Convert a JSON list into a List of asset passed as generic.
	 * 
	 * @param json
	 * @param elementClass
	 * @return List<T>
	 */
	private List<T> fromArrayToJSON(String json, Class<T> elementClass){
		List<T> asset = mapper.fromJson(json, new ElementList<T>(elementClass));
		return asset;
	}
	
	/*
	 * check if the json is an array. If the first character is a "["
	 */
	private boolean isArray(String json) {
		return (json.startsWith("["));
	}

	@Override
	public MapperType getType() {
		return MapperType.GSON;
	}
}
