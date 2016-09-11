package by.epam.hostelbeta.pool;
import org.junit.Test;

import by.epam.hostelbeta.pool.ConnectionPool;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionPoolTest.
 */
public class ConnectionPoolTest {
	
	/**
	 * Pool init and retrieve test.
	 */
	@Test (timeout = 1500)
	public void poolInitAndRetrieveTest(){
		ConnectionPool.getInstance().retrieve();
	}
}
