package com.javatraining.core.advanced;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Java17FeaturesTest {

    @Test
    void testRecord() {
        Java17Features.Person person = new Java17Features.Person("Alice", LocalDate.of(1990, 5, 15));
        
        assertEquals("Alice", person.name());
        assertNotNull(person.birthDate());
        assertTrue(person.getAge() > 0);
        
        // Test compact constructor validation
        assertThrows(IllegalArgumentException.class, 
            () -> new Java17Features.Person("", LocalDate.now()));
    }
    
    @Test
    void testShapeAreaCalculations() {
        Java17Features.Circle circle = new Java17Features.Circle(5.0);
        Java17Features.Rectangle rectangle = new Java17Features.Rectangle(4.0, 6.0);
        Java17Features.Triangle triangle = new Java17Features.Triangle(3.0, 4.0);
        
        assertEquals(Math.PI * 25, circle.area(), 0.001);
        assertEquals(24.0, rectangle.area(), 0.001);
        assertEquals(6.0, triangle.area(), 0.001);
    }
    
    @Test
    void testPatternMatching() {
        List<Java17Features.Person> people = List.of(
            new Java17Features.Person("Alice", LocalDate.now().minusYears(25)),
            new Java17Features.Person("Bob", LocalDate.now().minusYears(15)),
            new Java17Features.Person("Anna", LocalDate.now().minusYears(20))
        );
        
        // This test verifies the method runs without exceptions
        // In a real test, you might want to capture and verify the output
        assertDoesNotThrow(() -> Java17Features.processPeople(people));
    }
    
    @Test
    void testTextBlocks() {
        String html = Java17Features.getHtmlTemplate();
        
        assertNotNull(html);
        assertTrue(html.contains("<html>"));
        assertTrue(html.contains("Java 17 Features"));
        assertTrue(html.contains("</body>"));
    }
    
    @Test
    void testHelpfulNPE() {
        // This test verifies the NPE behavior
        assertDoesNotThrow(Java17Features::demonstrateHelpfulNPE);
    }
}
