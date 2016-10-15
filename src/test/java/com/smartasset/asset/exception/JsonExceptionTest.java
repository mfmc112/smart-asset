package com.smartasset.asset.exception;

import junit.framework.Assert;

import org.junit.Test;

public class JsonExceptionTest {

	
	@Test
	public void testNoMessage(){
		JsonException e = new JsonException();
		Assert.assertEquals("JSONException ocurrend", e.getMessage());
	}
	
	@Test
	public void testExceptionMessage(){
		JsonException e = new JsonException("Simple exception");
		Assert.assertEquals("Simple exception", e.getMessage());
	}
	
	@Test
	public void testNestedExceptionMessage(){
		JsonException e1 = new JsonException("Simple exception exception 1");
		JsonException e2 = new JsonException("Simple exception exception 2", e1);
		Assert.assertEquals("Simple exception exception 1", e1.getMessage());
		Assert.assertEquals("Simple exception exception 1", e2.getNestedException().getMessage());
	}
}
