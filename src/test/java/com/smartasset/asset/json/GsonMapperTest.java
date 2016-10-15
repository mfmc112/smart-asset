package com.smartasset.asset.json;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import com.smartasset.asset.exception.JsonException;
import com.smartasset.asset.test.Github;

public class GsonMapperTest {

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
	
	@Test
	public void gsonMapperArrayObject() throws JsonException{
		JsonMapper<Github> mapper = new GsonMapperImpl<Github>(Github.class);
		List<Github> list = mapper.readValue(jsonString);
		Assert.assertTrue(list.size() == 1);
		Assert.assertEquals("mfmc112", list.get(0).login);
	}
	
	@Test
	public void gsonMapperSingleObjectTest() throws JsonException{
		JsonMapper<Github> mapper = new GsonMapperImpl<Github>(Github.class);
		List<Github> list = mapper.readValue(jsonString.replace("[", "").substring(0, jsonString.length()-2));
		Assert.assertTrue(list.size() == 1);
		Assert.assertEquals("mfmc112", list.get(0).login);
	}
	
	@Test(expected=JsonException.class)
	public void gsonMapperInvalidObjectTest() throws JsonException{
		JsonMapper<Github> mapper = new GsonMapperImpl<Github>(Github.class);
		mapper.readValue(jsonString.substring(0, jsonString.length()-10));
		Assert.assertTrue(false);
	}
}
