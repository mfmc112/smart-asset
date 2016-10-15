package com.smartasset.asset.json;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smartasset.asset.exception.JsonException;
import com.smartasset.asset.json.type.Element;
import com.smartasset.asset.json.type.ElementList;

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
public class GsonMapperImpl<T> implements JsonMapper<T> {
	
	private Gson mapper;
	private Class<T> elementClass;
	
	public GsonMapperImpl(Class<T> elementClass){
		this.elementClass = elementClass;
		initialize();
		configureMapper();
	}
	
	private void initialize(){
		GsonBuilder builder  = new GsonBuilder();
		this.mapper = builder.create();
	}
	
	public void configureMapper(){
		// TODO: add configuration here if needed
	}

	@Override
	public List<T> readValue(String content) throws JsonException {
		List<T> list = new ArrayList<T>();
		try{
			if (isArray(content)){
				list = fromArrayToJSON(content, elementClass);
			} else{
				list.add(fromObjectToJSON(content, elementClass));
			}
		}catch(Exception e){
			throw new JsonException("Could not parse Json with Jackson", e);
		}
		return list;
	}
	
	/**
	 * Convert a single JSON to the Asset passed as generic.
	 * 
	 * @param json
	 * @param elementClass
	 * @return T
	 */
	protected T fromObjectToJSON(String json, Class<T> elementClass){
		T asset = mapper.fromJson(json, new Element<T>(elementClass));
		return asset;
	}

	/**
	 * Convert a JSON list into a List of asset passed as generic.
	 * 
	 * @param json
	 * @param elementClass
	 * @return List<T>
	 */
	protected List<T> fromArrayToJSON(String json, Class<T> elementClass){
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
