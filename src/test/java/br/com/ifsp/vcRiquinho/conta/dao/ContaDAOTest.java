package br.com.ifsp.vcRiquinho.conta.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class ContaDAOTest {

	@Test
	void findAllTest() {
		ContaDAO dao = new ContaDAO();
		
		assertNotNull(dao.findAll());
	}

	@Test
	void insertTestSucess() {
		ContaDAO dao = new ContaDAO();
		
		assertTrue(dao.insert(null));
	}
	
	@Test
	void insertTestFail() {
		ContaDAO dao = new ContaDAO();
		
		assertFalse(dao.insert(null));
	}

	@Test
	void deleteByTestSucess() {
		ContaDAO dao = new ContaDAO();
		
		assertTrue(dao.deleteBy(null));
	}
	
	@Test
	void deleteByTestFail() {
		ContaDAO dao = new ContaDAO();
		
		assertFalse(dao.deleteBy(null));
	}

	@Test
	void findByTestNotNull() {
		ContaDAO dao = new ContaDAO();
		
		assertNotNull(dao.findBy(null));
	}
	
	@Test
	void findByTestNull() {
		ContaDAO dao = new ContaDAO();
		
		assertNull(dao.findBy(null));
	}

	@Test
	void findOptioanlByTestNotEmpty() {
		ContaDAO dao = new ContaDAO();
		
		assertFalse(dao.findOptionalBy(null).isEmpty());
	}
	
	@Test
	void findOptioanlByTestEmpty() {
		ContaDAO dao = new ContaDAO();
		
		assertTrue(dao.findOptionalBy(null).isEmpty());
	}

	@Test
	void updateByTestNotNull() {
		ContaDAO dao = new ContaDAO();
		
		assertNotNull(dao.updateBy(null));
	}
	
	@Test
	void updateByTestNull() {
		ContaDAO dao = new ContaDAO();
		
		assertNull(dao.updateBy(null));
	}

	@Test
	void updateOptioanlByTestNotEmpty() {
		ContaDAO dao = new ContaDAO();
		
		assertFalse(dao.updateOptionalBy(null).isEmpty());
	}
	
	@Test
	void updateOptioanlByTestEmpty() {
		ContaDAO dao = new ContaDAO();
		
		assertTrue(dao.updateOptionalBy(null).isEmpty());
	}

}
