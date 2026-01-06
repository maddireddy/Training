package com.javatraining.core.concurrency;

import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicInteger;
import static org.junit.jupiter.api.Assertions.*;

class ConcurrencyDemoTest {

    @Test
    void testThreadCreation() throws InterruptedException {
        Thread testThread = new Thread(() -> System.out.println("Test thread running"));
        testThread.start();
        testThread.join(1000); // Wait for thread to complete with timeout
        
        assertFalse(testThread.isAlive());
    }
    
    @Test
    void testAtomicCounter() {
        AtomicInteger counter = new AtomicInteger(0);
        int result = counter.incrementAndGet();
        
        assertEquals(1, result);
        assertEquals(1, counter.get());
    }
    
    @Test
    void testExecutorService() {
        // This is a simple test to verify the executor service can run tasks
        // In a real test, you might want to use a mock or test-specific executor
        ConcurrencyDemo demo = new ConcurrencyDemo();
        assertDoesNotThrow(() -> ConcurrencyDemo.main(new String[]{}));
    }
}
