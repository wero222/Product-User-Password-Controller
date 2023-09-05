package it.corso.dao;
import org.springframework.data.repository.CrudRepository;
import it.corso.model.User;

public interface UserDao extends CrudRepository<User, Integer>
{
	User findByMail(String mail);
	User findByMailAndPassword(String mail, String password);
	User findByAuthToken(String authToken);
}