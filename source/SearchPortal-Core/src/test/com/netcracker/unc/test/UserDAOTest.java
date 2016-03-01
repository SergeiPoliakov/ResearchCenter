package com.netcracker.unc.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.util.reflection.Fields;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.netcracker.unc.mvc.dao.UserDAO;
@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest {
	@Mock
	DataSource mockDataSource;
	@Mock
	Connection mockConnection;
	@Mock
	PreparedStatement mockPrepStatement;
	@Mock
	ResultSet mockResultSet;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws SQLException{
		when(mockDataSource.getConnection()).thenReturn(mockConnection);
		when(mockDataSource.getConnection(anyString(), anyString())).thenReturn(mockConnection);
        doNothing().when(mockConnection).commit();
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockPrepStatement);
        doNothing().when(mockPrepStatement).setString(anyInt(), anyString());
        when(mockPrepStatement.execute()).thenReturn(Boolean.TRUE);
        when(mockPrepStatement.getGeneratedKeys()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);
       // when(mockResultSet.getInt()).thenReturn(userId);
	}
	@Test
	public void testGetAllObjectsDB() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddObject() {
		UserDAO instance = new UserDAO();//нужно сделать mock connectiona из ObjectDAO
		
		
	}

	@Test
	public void testGetObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteObject() {
		fail("Not yet implemented");
	}

}
