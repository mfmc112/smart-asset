package com.smartasset.asset.json;

public class JsonMapperFactory {

	private static final MapperType DEFAULT_TYPE = MapperType.JACKSON;
	
	public static <T> JsonMapper<T> createFactory(MapperType type, Class<T> elementClass){
		if (type == null) type = DEFAULT_TYPE;
		
		JsonMapper<T> mapper = null;
		switch(type){
			case GSON:
				mapper = new BaseGsonMapper<T>(elementClass);
				break;		
			case JACKSON:
			default:
				mapper = new BaseJacksonMapper<T>(elementClass);
				break;
		}
		return mapper;
	}
}
