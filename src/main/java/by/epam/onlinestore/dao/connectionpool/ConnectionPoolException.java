package by.epam.onlinestore.dao.connectionpool;

public class ConnectionPoolException extends Exception {
    public ConnectionPoolException(String message, Exception exception) {
        super(message, exception);
    }

    public ConnectionPoolException(String message) {
        super(message);
    }

    public ConnectionPoolException(Exception exception) {
        super(exception);
    }
}
