package it.corso.service;
import java.util.List;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.corso.dto.UserDto;
import it.corso.model.User;

public interface UserService
{
	ObjectNode userRegistration(User user);
	UserDto getUserByToken(String token);
	ObjectNode updateUserData(User user, String token);
	ObjectNode deleteUser(String token);
	List<UserDto> getUsers();
	ObjectNode userLoginCheck(User user);
	ObjectNode userLogout(String token);
}