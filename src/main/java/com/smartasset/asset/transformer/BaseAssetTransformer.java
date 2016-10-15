package com.smartasset.asset.transformer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.smartasset.asset.exception.JsonException;
import com.smartasset.asset.json.JacksonMapperImpl;
import com.smartasset.asset.json.JsonMapper;
import com.smartasset.asset.json.MapperType;

public class BaseAssetTransformer<T> implements AssetTransformer<T> {

	Class<T> elementClass;
	
	private JsonMapper<T> mapper = null; 
	
	public BaseAssetTransformer(Class<T> elementClass) {
		 this.elementClass = elementClass;
	}
	
	@Override
	public List<T> toAsset(InputStream inputStream) throws JsonException {
		String data = readInputStream(inputStream);
		return toAsset(data);
	}
	
	@Override
	public List<T> toAsset(String data) throws JsonException {
		if (mapper == null) mapper = new JacksonMapperImpl<T>(elementClass);
		List<T> list = mapper.readValue(data);
		return list;
	}
	
	private String readInputStream(InputStream content){
		String entity = null;
		StringBuilder response = new StringBuilder();
		try {
	    	BufferedReader in = new BufferedReader (new InputStreamReader (content));
	        String line;
			while ((line = in.readLine()) != null) {
				response.append(line);
			}
			entity = response.toString();
		} catch (IOException e) {
			// TODO return proper exception here
			e.printStackTrace();
		}
		return entity;
	}

	/**
	 * Changes the JSONMapper to be used to convert from and to JSON.
	 * Default is JacksonMapper.class
	 *  
	 * @param mapper
	 */
	public void setJSONMapper(JsonMapper<T> mapper) {
		this.mapper = mapper;
	}

	@Override
	public MapperType getMapperType() {
		if (this.mapper == null) return null;
		return this.mapper.getType();
	}
	
}
