package by.epam.hostelbeta.pool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.hostelbeta.config.DBConfig;

public class ConnectionPool {
	static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

	private static final int POOL_SIZE = 20;

	private static AtomicBoolean isCreated = new AtomicBoolean();
	private static Lock lock = new ReentrantLock();
	private static ConnectionPool instance;

	private ArrayBlockingQueue<Connection> connections;

	private ConnectionPool() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			connections = new ArrayBlockingQueue<Connection>(POOL_SIZE);
			Connection connection = null;
			for (int i = 0; i < POOL_SIZE; i++) {
				connection = getConnection();
				if (connection != null) {
					connections.add(connection);
				}
			}
		} catch (SQLException e) {
			LOGGER.fatal("Error registering Driver jdbc", e);
			throw new RuntimeException(e);
		}
	}

	public static ConnectionPool getInstance() {
		if (!isCreated.get()) {
			try {
				lock.lock();
				if (instance == null) {
					instance = new ConnectionPool();
					isCreated.getAndSet(true);
				}
			} finally {
				lock.unlock();
			}
		}
		return instance;
	}

	private Connection getConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBConfig.getProperty(DBConfig.URL),
					DBConfig.getProperty(DBConfig.USER), DBConfig.getProperty(DBConfig.PASSWORD));
		} catch (SQLException e) {
			LOGGER.error("Error connection to DB!", e);
			throw new RuntimeException(e);
		}
		return connection;
	}

	public ConnectionDecorator retrieve() {
		ConnectionDecorator connection = null;
		try {
			connection = new ConnectionDecorator(connections.take());
			LOGGER.debug("Connectionn is token");
		} catch (InterruptedException e) {
			LOGGER.error("Error trying retrieve connection", e);
		}
		return connection;
	}

	void putback(Connection connection) {
		try {
			connections.put(connection);
			LOGGER.debug("Connection putback");
		} catch (InterruptedException e) {
			LOGGER.error("Error trying putback connection to pool", e);
		}
	}

	public void closePool() {
		while (connections != null && connections.size() > 0) {
			try {
				Connection connection = connections.take();
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException | InterruptedException e) {
				LOGGER.error("Error closing connection", e);
			}
		}
	}

	public int getSize() {
		return connections.size();
	}
}
