package com.xevgnov.scopes.custom;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheScope implements Scope {

    public static final String CACHE_SCOPE = "cache";
    private Cache<String, Object> beans;
    private Map<String, Runnable> destructionCallbacks = new ConcurrentHashMap<>();

    public CacheScope(Duration duration) {
        beans = CacheBuilder.newBuilder()
                .expireAfterAccess(duration)
                .build();
    }

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        try {
            return beans.get(name, () -> objectFactory.getObject());
        } catch (ExecutionException e) {
            log.error(name + " bean cannot be taken from cache, creating new instance", e);
            return objectFactory.getObject();
        }
    }

    @Override
    public Object remove(String name) {
        Object bean = beans.getIfPresent(name);
        destructionCallbacks.remove(name);
        beans.invalidate(name);
        return bean;
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
        return CACHE_SCOPE;
    }

}
