package com.smartasset.asset.json;

import java.util.List;

import com.smartasset.asset.exception.JsonException;


public interface JsonMapper<T> {

	public List<T> readValue(String content) throws JsonException;
	
	public T readSingleValue(String content) throws JsonException;
	
	public MapperType getType();
}
