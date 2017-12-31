package data;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Account;
import entities.User;

@Transactional
@Repository
public class UserDAOImpl implements UserDAO{
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<User> index() {
		String query = "SELECT u FROM User u"; 
		return em.createQuery(query, User.class)
				.getResultList(); 
	}

	@Override
	public User show(int id) {
		return em.find(User.class, id);
	}

	@Override
	public User create(String json) {
		ObjectMapper mapper = new ObjectMapper(); 
		User u = null; 
		try {
			u = mapper.readValue(json, User.class);
			em.persist(u);
			em.flush();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return em.find(User.class, u.getId()); 
	}

	@Override
	public User update(int id, String json) {
		ObjectMapper mapper = new ObjectMapper(); 
		User u = null; 
		User jsonObject = null; 
		try {
			jsonObject = mapper.readValue(json, User.class);
			u = em.find(User.class, id);
			u.setFirstName(jsonObject.getFirstName());
			u.setLastName(jsonObject.getLastName());
			u.setEmail(jsonObject.getEmail());
			u.setPassword(jsonObject.getPassword());
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return u; 
	}

	@Override
	public User delete(int id) {
		User u = em.find(User.class, id);
		em.remove(u);
		return u;
	} 
}
