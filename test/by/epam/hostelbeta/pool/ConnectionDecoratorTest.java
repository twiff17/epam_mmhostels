package by.epam.hostelbeta.pool;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConnectionDecoratorTest {

	@Test
	public void closeTest() {
		int expected = ConnectionPool.getInstance().getSize();
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		connection.close();
		int actual = ConnectionPool.getInstance().getSize();
		assertEquals(expected, actual);
	}

}
