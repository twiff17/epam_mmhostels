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

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionPool.
 */
public class ConnectionPool {
	
	/** The Constant LOGGER. */
	static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);

	/** The Constant POOL_SIZE. */
	private static final int POOL_SIZE = 20;

	/** The is created. */
	private static AtomicBoolean isCreated = new AtomicBoolean();
	
	/** The lock. */
	private static Lock lock = new ReentrantLock();
	
	/** The instance. */
	private static ConnectionPool instance;

	/** The connections. */
	private ArrayBlockingQueue<Connection> connections;

	/**
	 * Instantiates a new connection pool.
	 */
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

	/**
	 * Gets the single instance of ConnectionPool.
	 *
	 * @return single instance of ConnectionPool
	 */
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

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
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

	/**
	 * Retrieve.
	 *
	 * @return the connection decorator
	 */
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

	/**
	 * Putback.
	 *
	 * @param connection the connection
	 */
	void putback(Connection connection) {
		try {
			connections.put(connection);
			LOGGER.debug("Connection putback");
		} catch (InterruptedException e) {
			LOGGER.error("Error trying putback connection to pool", e);
		}
	}

	/**
	 * Close pool.
	 */
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

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return connections.size();
	}
}
