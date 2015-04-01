/*
 * Copyright 2015 Uttesh Kumar T.H..
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.uttesh.pdfngreport.util;

import java.util.HashMap;
import java.util.Set;

/**
 *PDF Library cache for the exception storing
 * @author Uttesh Kumar T.H.
 */
public class PDFCache {

    private static HashMap<Object, Object> cache = new HashMap<Object, Object>();
    private static HashMap<Object, Object> configCache = new HashMap<Object, Object>();

    public static Object get(Object key) {
        return cache.get(key);
    }

    public static void put(Object key, Object value) {
        cache.put(key, value);
    }

    public static Set<Object> getAllKeys() {
        return cache.keySet();
    }

    public static HashMap<Object, Object> getCache() {
        return cache;
    }

    public static void setCache(HashMap<Object, Object> cache) {
        PDFCache.cache = cache;
    }

    public static HashMap<Object, Object> getConfigCache() {
        return configCache;
    }

    public static void setConfigCache(HashMap<Object, Object> configCache) {
        PDFCache.configCache = configCache;
    }
    
    
    public static Object getConfig(Object key) {
        return configCache.get(key);
    }

    public static void putConfig(Object key, Object value) {
        configCache.put(key, value);
    }
    
}
