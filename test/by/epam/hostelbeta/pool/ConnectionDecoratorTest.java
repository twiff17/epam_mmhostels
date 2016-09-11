package by.epam.hostelbeta.pool;

import static org.junit.Assert.*;

import org.junit.Test;

// TODO: Auto-generated Javadoc
/**
 * The Class ConnectionDecoratorTest.
 */
public class ConnectionDecoratorTest {

	/**
	 * Close test.
	 */
	@Test
	public void closeTest() {
		int expected = ConnectionPool.getInstance().getSize();
		ConnectionDecorator connection = ConnectionPool.getInstance().retrieve();
		connection.close();
		int actual = ConnectionPool.getInstance().getSize();
		assertEquals(expected, actual);
	}

}
