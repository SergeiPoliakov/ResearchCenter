package com.netcracker.unc.modelTests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import com.netcracker.unc.mvc.models.AttributeModel;

@RunWith(PowerMockRunner.class)
@PrepareForTest({String.class, Integer.class})
public class AttributeModelTest {
	private final Integer intMock = PowerMockito.mock(Integer.class);
	private final String stringMock = PowerMockito.mock(String.class);
	private AttributeModel modelUnderTest;

	@Before
	public void setUp() throws Exception {
		
		modelUnderTest = new AttributeModel();
	}

	@Test
	public void testGetAttributeName() {
		
		
	}

	@Test
	public void testSetAttributeName() {
		modelUnderTest.setAttributeName(anyString());
		verify(modelUnderTest).setAttributeName(anyString());
	}

	@Test
	public void testGetFinObjectTypeId() {
		modelUnderTest.getFinObjectTypeID();
		verify(modelUnderTest).getFinObjectTypeID();
	}

	@Test
	public void testSetFinObjectTypeId() {
		modelUnderTest.setFinObjectTypeID(anyInt());
		verify(modelUnderTest).setFinObjectTypeID(anyInt());
	}

	@Test
	public void testGetAttributeId() {
		modelUnderTest.getAttributeID();
		//verify(modelUnderTest).
	}

	@Test
	public void testSet_attribute_id() {
		fail("Not yet implemented");
	}

}
