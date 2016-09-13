package by.epam.hostelbeta.validator;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import by.epam.hostelbeta.domain.entity.Hostel;

/**
 * The Class HostelValidatorTest.
 */
public class HostelValidatorTest {
	
	/** The hostel. */
	private Hostel hostel = new Hostel();

	/**
	 * Fill hostel.
	 */
	@Before
	public void fillHostel() {
		hostel.setAddress("������������� 22�");
		hostel.setCity("�����");
		hostel.setCountry("��������");
		hostel.setCurrency("BYN");
		hostel.setDescription("������� ������");
		hostel.setHostelId(1);
		hostel.setImageName("mink.jpg");
		hostel.setName("�� ������");
		hostel.setPhone("(017)333-34-11");
		hostel.setStandartPrice(23);
	}

	/**
	 * Adds the validate test.
	 */
	@Test
	public void addValidateTest() {
		assertTrue(HostelValidator.addingValidate(hostel));
		hostel.setImageName("image.exe");
		assertFalse(HostelValidator.addingValidate(hostel));
	}
}
