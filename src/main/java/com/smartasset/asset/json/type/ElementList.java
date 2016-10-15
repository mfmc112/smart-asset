package com.smartasset.asset.json.type;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Class responsible for initializing the type List<T> defining the correct Generics 
 * to be parsed by GSON. Without this class the GSON.fromJson returns a 
 * List<LinkedHashMap>

 * @author Marcos Costa
 *
 * @param <T>
 */
public class ElementList<T> extends Element<T> implements ParameterizedType {

    public ElementList(Class<T> cl) {
        super(cl);
    }

    public Type getRawType() {
        return List.class;
    }

    public Type getOwnerType() {
        return null;
    }
}