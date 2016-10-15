package com.smartasset.asset.transformer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.smartasset.asset.json.BaseGsonMapper;
import com.smartasset.asset.json.BaseJacksonMapper;
import com.smartasset.asset.json.JsonMapperFactory;
import com.smartasset.asset.json.MapperType;
import com.smartasset.asset.test.Github;

public class BaseAssetTransformerTest {

	public  InputStream content; 
	
	String jsonString = "[{ " +
			  "\"login\": \"mfmc112\", " +
			  "\"id\": 1028680, " +
			  "\"avatar_url\": \"https://avatars.githubusercontent.com/u/1028680?v=3\", " +
			  "\"gravatar_id\": \"\", " +
			  "\"url\": \"https://api.github.com/users/mfmc112\", " +
			  "\"html_url\": \"https://github.com/mfmc112\", " +
			  "\"followers_url\": \"https://api.github.com/users/mfmc112/followers\", " +
			  "\"following_url\": \"https://api.github.com/users/mfmc112/following{/other_user}\", " +
			  "\"gists_url\": \"https://api.github.com/users/mfmc112/gists{/gist_id}\", " +
			  "\"starred_url\": \"https://api.github.com/users/mfmc112/starred{/owner}{/repo}\", " +
			  "\"subscriptions_url\": \"https://api.github.com/users/mfmc112/subscriptions\", " +
			  "\"organizations_url\": \"https://api.github.com/users/mfmc112/orgs\", " +
			  "\"repos_url\": \"https://api.github.com/users/mfmc112/repos\", " +
			  "\"events_url\": \"https://api.github.com/users/mfmc112/events{/privacy}\", " +
			  "\"received_events_url\": \"https://api.github.com/users/mfmc112/received_events\", " +
			  "\"type\": \"User\", " +
			  "\"site_admin\": false, " +
			  "\"name\": \"Marcos Costa\", " +
			  "\"company\": \"Availit LLC\", " +
			  "\"blog\": null, " +
			  "\"location\": \"Dallas, TX\", " +
			  "\"email\": \"mfmc112@gmail.com\", " +
			  "\"hireable\": null, " +
			  "\"bio\": null, " +
			  "\"public_repos\": 4, " +
			  "\"public_gists\": 0, " +
			  "\"followers\": 1, " +
			  "\"following\": 0, " +
			  "\"created_at\": \"2011-09-06T01:06:35Z\", " +
			  "\"updated_at\": \"2016-10-09T23:29:35Z\" " +
			"}]";
	
	InputStream inputStream;
	
	@Before
	public void init(){
		inputStream = new ByteArrayInputStream(jsonString.getBytes(StandardCharsets.UTF_8));
	}
	
	@Test
	public void inputStreamDefaultMapper() throws Exception{
		AssetTransformer<Github> transformer = new BaseAssetTransformer<Github>(Github.class);
		List<Github> result = transformer.toAsset(inputStream);
	    
		Assert.assertTrue(result.size() == 1);
	    Assert.assertEquals("mfmc112",result.get(0).login);
	    Assert.assertEquals(MapperType.JACKSON, transformer.getMapperType());
	}

	@Test
	public void inputStreamGsonMapper() throws Exception{
		AssetTransformer<Github> transformer = new BaseAssetTransformer<Github>(Github.class);
		transformer.setJSONMapper(new BaseGsonMapper<Github>(Github.class));
		List<Github> result = transformer.toAsset(inputStream);
		
	    Assert.assertTrue(result.size() == 1);
	    Assert.assertEquals("mfmc112",result.get(0).login);
	    Assert.assertEquals(MapperType.GSON, transformer.getMapperType());
	}
	
	@Test
	public void inputStreamGsonMapperFromFactory() throws Exception{
		AssetTransformer<Github> transformer = new BaseAssetTransformer<Github>(Github.class);
		transformer.setJSONMapper(JsonMapperFactory.createFactory(MapperType.GSON, Github.class));
		List<Github> result = transformer.toAsset(inputStream);
		
	    Assert.assertTrue(result.size() == 1);
	    Assert.assertEquals("mfmc112",result.get(0).login);
	    Assert.assertEquals(MapperType.GSON, transformer.getMapperType());

	}
	
	@Test
	public void inputStreamJacksonMapper() throws Exception{
		AssetTransformer<Github> transformer = new BaseAssetTransformer<Github>(Github.class);
		transformer.setJSONMapper(new BaseJacksonMapper<Github>(Github.class));
		List<Github> result = transformer.toAsset(inputStream);
		
	    Assert.assertTrue(result.size() == 1);
	    Assert.assertEquals("mfmc112",result.get(0).login);
	    Assert.assertEquals(MapperType.JACKSON, transformer.getMapperType());

	}
	
	@Test
	public void inputStreamJacksonMapperfromFactory() throws Exception{
		AssetTransformer<Github> transformer = new BaseAssetTransformer<Github>(Github.class);
		transformer.setJSONMapper(JsonMapperFactory.createFactory(MapperType.JACKSON, Github.class));
		List<Github> result = transformer.toAsset(inputStream);
		
	    Assert.assertTrue(result.size() == 1);
	    Assert.assertEquals("mfmc112",result.get(0).login);
	    Assert.assertEquals(MapperType.JACKSON, transformer.getMapperType());

	}
	
	@Test
	public void stringDefaultMapper() throws Exception{
		AssetTransformer<Github> transformer = new BaseAssetTransformer<Github>(Github.class);
		List<Github> result = transformer.toAsset(jsonString);
	    
		Assert.assertTrue(result.size() == 1);
	    Assert.assertEquals("mfmc112",result.get(0).login);
	    Assert.assertEquals(MapperType.JACKSON, transformer.getMapperType());
	}

	@Test
	public void stringGsonMapper() throws Exception{
		AssetTransformer<Github> transformer = new BaseAssetTransformer<Github>(Github.class);
		transformer.setJSONMapper(new BaseGsonMapper<Github>(Github.class));
		List<Github> result = transformer.toAsset(jsonString);
		
	    Assert.assertTrue(result.size() == 1);
	    Assert.assertEquals("mfmc112",result.get(0).login);
	    Assert.assertEquals(MapperType.GSON, transformer.getMapperType());
	}
	
	@Test
	public void stringGsonMapperFromFactory() throws Exception{
		AssetTransformer<Github> transformer = new BaseAssetTransformer<Github>(Github.class);
		transformer.setJSONMapper(JsonMapperFactory.createFactory(MapperType.GSON, Github.class));
		List<Github> result = transformer.toAsset(jsonString);
		
	    Assert.assertTrue(result.size() == 1);
	    Assert.assertEquals("mfmc112",result.get(0).login);
	    Assert.assertEquals(MapperType.GSON, transformer.getMapperType());
	}
	
	@Test
	public void fromStringJacksonMapper() throws Exception{
		AssetTransformer<Github> transformer = new BaseAssetTransformer<Github>(Github.class);
		transformer.setJSONMapper(new BaseJacksonMapper<Github>(Github.class));
		List<Github> result = transformer.toAsset(jsonString);
		
	    Assert.assertTrue(result.size() == 1);
	    Assert.assertEquals("mfmc112",result.get(0).login);
	    Assert.assertEquals(MapperType.JACKSON, transformer.getMapperType());
	}
	
	@Test
	public void stringJacksonMapperFromFactory() throws Exception{
		AssetTransformer<Github> transformer = new BaseAssetTransformer<Github>(Github.class);
		transformer.setJSONMapper(JsonMapperFactory.createFactory(MapperType.JACKSON, Github.class));
		List<Github> result = transformer.toAsset(jsonString);
		
	    Assert.assertTrue(result.size() == 1);
	    Assert.assertEquals("mfmc112",result.get(0).login);
	    Assert.assertEquals(MapperType.JACKSON, transformer.getMapperType());
	}
	
	
}
