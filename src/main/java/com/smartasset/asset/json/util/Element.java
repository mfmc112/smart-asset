package com.smartasset.asset.json.util;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Class responsible for initializing the type T defining the correct Generics to be  
 * parsed by GSON. Without this class the GSON.fromJson returns a List<LinkedHashMap>.
 * Used to define Single Object.

 * @author Marcos Costa
 *
 * @param <T>
 */
public class Element<T> implements ParameterizedType {

    private Class<T> cl;

    public Element(Class<T> cl) {
        this.cl = cl;
    }

    public Type[] getActualTypeArguments() {
        return new Type[] {cl};
    }

    public Type getRawType() {
        return cl;
    }

    public Type getOwnerType() {
        return null;
    }
}