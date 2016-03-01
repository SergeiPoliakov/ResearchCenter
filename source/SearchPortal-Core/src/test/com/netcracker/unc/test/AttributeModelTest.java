package com.netcracker.unc.test;
import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.internal.runners.TestClass;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.*;

import javax.naming.spi.DirStateFactory.Result;

import com.netcracker.unc.mvc.models.AttributeModel;
public class AttributeModelTest {
	private static AttributeModel testClass;
	private static AttributeModel result;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testClass = mock(AttributeModel.class);
		result = new AttributeModel();
	}


	@Test
	public void testGetSet_attribute_name() {
		result.set_attribute_name("foo");
		
		assertEquals("foo", result.get_attribute_name());
		
	}

	

	@Test
	public void testGetSet_fin_object_type_id() {
		result.set_fin_object_type_id(202);
		assertEquals(202, result.get_fin_object_type_id());
		
	}


	@Test
	public void testGetSet_attribute_id() {
		result.set_attribute_id(101);
		assertEquals(101, result.get_attribute_id());
	}

}
