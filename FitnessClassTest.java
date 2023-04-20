import static org.junit.Assert.*;
import org.junit.*;

import java.util.List;

public class FitnessClassTest {
    private FitnessClass fitnessClass;

    @Before
    public void setUp() {
        fitnessClass = new FitnessClass("Yoga", "Monday", 9, 10);
    }

    @Test
    public void testGetters() {
        assertEquals("Yoga", fitnessClass.getType());
        assertEquals("Monday", fitnessClass.getDay());
        assertEquals(9, fitnessClass.getTime());
        assertEquals(10, fitnessClass.getPrice());
    }

    @Test
    public void testIsFull() {
        assertFalse(fitnessClass.isFull());
        fitnessClass.addCustomer("Alice");
        fitnessClass.addCustomer("Bob");
        fitnessClass.addCustomer("Charlie");
        fitnessClass.addCustomer("Dave");
        fitnessClass.addCustomer("Eve");
        assertTrue(fitnessClass.isFull());
    }

    
    @Test
    public void testGetCustomers() {
        fitnessClass.addCustomer("Alice");
        fitnessClass.addCustomer("Bob");
        List<String> customers = fitnessClass.getCustomers();
        assertEquals(2, customers.size());
        assertTrue(customers.contains("Alice"));
        assertTrue(customers.contains("Bob"));
    }

    @Test
    public void testRating() {
        fitnessClass.setRating(3.5);
        assertEquals(3.5, fitnessClass.getRating(), 0.01);
    }

    @Test
    public void testStatus() {
        assertEquals("booked", fitnessClass.getStatus());
        fitnessClass.setStatus("cancelled");
        assertEquals("cancelled", fitnessClass.getStatus());
    }

    @Test
    public void testCapacity() {
        assertEquals(5, fitnessClass.getCapacity());
    }
}
