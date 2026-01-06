package com.javatraining.core.collections;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class CollectionsDemoTest {

    @Test
    void testArrayListOperations() {
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        
        assertEquals(2, list.size());
        assertTrue(list.contains("Java"));
        assertEquals("Python", list.get(1));
    }
    
    @Test
    void testHashSetOperations() {
        Set<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Apple"); // Duplicate
        
        assertEquals(1, set.size());
        assertTrue(set.contains("Apple"));
    }
    
    @Test
    void testHashMapOperations() {
        Map<String, Integer> map = new HashMap<>();
        map.put("One", 1);
        map.put("Two", 2);
        
        assertEquals(2, map.size());
        assertEquals(1, map.get("One"));
        assertTrue(map.containsKey("Two"));
    }
}
