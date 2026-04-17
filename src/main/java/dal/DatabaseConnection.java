package dal;

import java.sql.Connection;

public class DatabaseConnection {
    
    // yeh variable sirf ek baar banta hai — Singleton ka core
    private static DatabaseConnection INSTANCE;
    
    // Connection object
    private Connection connection;

    // private constructor — bahar se new DatabaseConnection() nahi ho sakta
    // Jaise PM office ka darwaza — seedha andar nahi aa sakte
    private DatabaseConnection() {
        // actual DB connection yahan hoga
        // testing mein hum ise mock karenge
        this.connection = null;
    }

    // sirf yeh method se instance milta hai
    // synchronized matlab ek waqt mein sirf ek thread access kare
    public static synchronized DatabaseConnection getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseConnection();
        }
        return INSTANCE;
    }

    public Connection getConnection() {
        return connection;
    }

    // testing ke liye — instance reset karna
    // yeh sirf tests mein use hoga
    public static void resetInstance() {
        INSTANCE = null;
    }
}