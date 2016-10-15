package com.smartasset.asset.transformer;

import java.io.InputStream;
import java.util.List;

import com.smartasset.asset.exception.JsonException;
import com.smartasset.asset.json.JsonMapper;
import com.smartasset.asset.json.MapperType;

public interface AssetTransformer<T> {
	
	public List<T> toAsset(InputStream inputStream) throws JsonException;

	public List<T> toAsset(String data) throws JsonException;

	public void setJSONMapper(JsonMapper<T> mapper);
	
	public MapperType getMapperType();
	
}
