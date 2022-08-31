package by.epam.onlinestore.dao.connection;

import by.epam.onlinestore.dao.connectionPool.ConnectionPool;
import by.epam.onlinestore.dao.connectionPool.ConnectionPoolException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConnectionPoolTest {

    @BeforeAll
    static void initializeConnectionPool() throws ConnectionPoolException {
        ConnectionPool.getInstance().initPoolData();

    }

    @Test
    void testGetInstance_ShouldReturnTheSameClass() {

        ConnectionPool first = ConnectionPool.getInstance();
        ConnectionPool second = ConnectionPool.getInstance();

        assertEquals(first, second);
    }

    @Test
    void testTakeConnection_ShouldReturnTrue_IfConnectionValidTenSeconds() throws ConnectionPoolException, SQLException {
        assertTrue(ConnectionPool.getInstance().takeConnection().isValid(10));
    }
}
