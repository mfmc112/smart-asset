# smart-asset
It facilitates the conversion between JSON and asset class. 

If you make API calls and the response is JSON this small project might help you.

## Usage
This is all you need:
if your result is a JSON as String or even as InputStream.
```java
  AssetTransformer<Github> transformer = new BaseAssetTransformer<Github>(Github.class);
  List<Github> result = transformer.toAsset( jsonStringOrInputStream );
```

## Default Parser
By default it used **Jackson** with basic configuration. which is
```java
  FAIL_ON_UNKNOWN_PROPERTIES = false
  ACCEPT_SINGLE_VALUE_AS_ARRAY = true
  ACCEPT_EMPTY_STRING_AS_NULL_OBJECT = true
```

## Changing the Default Parser
after creating the transformer you can set the desired JSON mapper before executing toAsset method
```java
  transformer.setJSONMapper(JsonMapperFactory.createFactory(MapperType.GSON, Github.class));
```

## Extending JSON Mapper
As for now there are two classes inmplemented to parse JSON. They are BaseGsonMapper and BaseJacksonMapper. Extend those classes and Override the setMapperConfiguration() method setting the new configuration required.
If you create SpecialJacksonMapper for example it will look like this
```
public class SpecialJacksonMapper<T> extends BaseJacksonMapper<T> {

	public SpecialJacksonMapper(Class<T> referenceClass) {
		super(referenceClass);
	}
	
	
	protected void setMapperConfiguration() {
		mapper.configure(Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, false);
		mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);
	}

}

```


The usage of your new class should be simple
```
  JsonMapper<Github> mapper = new SpecialJacksonMapper<Github>(Github.class);
  List<Github> list = mapper.readValue(jsonString);
```

## What is next
Make BaseAssetTransformer extendable easier.

Implementation of the fromJSON.

Adding support to XML




