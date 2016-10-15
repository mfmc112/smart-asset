package com.smartasset.asset.json;

import junit.framework.Assert;

import org.junit.Test;

import com.smartasset.asset.test.Github;

public class JsonMapperFactoryTest {

	@Test
	public void testNullTypeFactory(){
		JsonMapper<Github> mapper = JsonMapperFactory.createFactory(null, Github.class);
		Assert.assertEquals(MapperType.JACKSON, mapper.getType());
	}
	
	@Test
	public void testGsonFactory(){
		JsonMapper<Github> mapper = JsonMapperFactory.createFactory(MapperType.GSON, Github.class);
		Assert.assertEquals(MapperType.GSON, mapper.getType());
	}
	
	@Test
	public void testJacksonFactory(){
		JsonMapper<Github> mapper = JsonMapperFactory.createFactory(MapperType.JACKSON, Github.class);
		Assert.assertEquals(MapperType.JACKSON, mapper.getType());
	}
}
