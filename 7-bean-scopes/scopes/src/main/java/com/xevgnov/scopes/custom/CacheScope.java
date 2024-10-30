package com.xevgnov.scopes.custom;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class CacheScope implements Scope {

    private Map<String, Object> beans = new ConcurrentHashMap<>();
    private Map<String, Runnable> destructionCallbacks = new ConcurrentHashMap<>();

    // to do caching
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        if (!beans.containsKey(name)) {
            beans.put(name, objectFactory.getObject());
        }
        return beans.get(name);
    }

    @Override
    public Object remove(String name) {
        destructionCallbacks.remove(name);
        return beans.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        destructionCallbacks.put(name, callback);
    }

    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }

    @Override
    public String getConversationId() {
        return "Cache";
    }

}
