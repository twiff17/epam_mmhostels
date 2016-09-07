package by.epam.hostelbeta.pool;
import org.junit.Test;

import by.epam.hostelbeta.pool.ConnectionPool;

public class ConnectionPoolTest {
	@Test (timeout = 1500)
	public void poolInitAndRetrieveTest(){
		ConnectionPool.getInstance().retrieve();
	}
}
