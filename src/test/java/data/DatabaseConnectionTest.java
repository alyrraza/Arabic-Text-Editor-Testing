package data;

import dal.DatabaseConnection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectionTest {

    @BeforeEach
    public void setUp() {
        // har test se pehle Singleton reset karo
        // taake har test fresh start kare
        // Jaise har test se pehle PM office khali karo
        DatabaseConnection.resetInstance();
    }

    // ============================================
    // TEST 1 — Singleton Core Test
    // Ek hi instance hona chahiye hamesha
    // ============================================
    @Test
    public void testGetInstance_ShouldReturnSameInstance() {
        // Action — do baar getInstance() call karo
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        DatabaseConnection instance2 = DatabaseConnection.getInstance();

        // Assert — dono same object hone chahiye
        // assertSame matlab: memory mein literally same object
        // assertEquals se alag — woh values compare karta hai
        // assertSame object reference compare karta hai
        assertSame(instance1, instance2);
    }

    // ============================================
    // TEST 2 — Singleton Not Null Test
    // getInstance() null return nahi karna chahiye
    // ============================================
    @Test
    public void testGetInstance_ShouldNotReturnNull() {
        // Action
        DatabaseConnection instance = DatabaseConnection.getInstance();

        // Assert — null nahi hona chahiye
        assertNotNull(instance);
    }

    // ============================================
    // TEST 3 — Multiple Calls Test
    // Kitni baar bhi bulao — same instance mile
    // ============================================
    @Test
    public void testGetInstance_MultipleCallsShouldReturnSameInstance() {
        // Action — teen baar bulao
        DatabaseConnection instance1 = DatabaseConnection.getInstance();
        DatabaseConnection instance2 = DatabaseConnection.getInstance();
        DatabaseConnection instance3 = DatabaseConnection.getInstance();

        // Assert — teeno same hone chahiye
        assertSame(instance1, instance2);
        assertSame(instance2, instance3);
        assertSame(instance1, instance3);
    }

    // ============================================
    // TEST 4 — Singleton Class Type Test
    // Instance DatabaseConnection ka hona chahiye
    // ============================================
    @Test
    public void testGetInstance_ShouldReturnDatabaseConnectionType() {
        // Action
        DatabaseConnection instance = DatabaseConnection.getInstance();

        // Assert — sahi class type hona chahiye
        // assertInstanceOf matlab: yeh object is class ka instance hai
        assertInstanceOf(DatabaseConnection.class, instance);
    }

    // ============================================
    // TEST 5 — Reset Test
    // Reset ke baad naya instance bane
    // ============================================
    @Test
    public void testResetInstance_ShouldAllowNewInstanceCreation() {
        // Action — pehla instance lo
        DatabaseConnection instance1 = DatabaseConnection.getInstance();

        // Reset karo
        DatabaseConnection.resetInstance();

        // Naya instance lo
        DatabaseConnection instance2 = DatabaseConnection.getInstance();

        // Assert — dono alag objects hone chahiye
        // assertNotSame matlab: alag alag objects hone chahiye
        assertNotSame(instance1, instance2);
    }
}