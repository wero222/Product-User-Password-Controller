package it.corso.service;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.corso.dao.UserDao;
import it.corso.dto.UserDto;
import it.corso.helper.ResponseManager;
import it.corso.helper.SecurityManager;
import it.corso.model.User;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public ObjectNode userRegistration(User user)
	{
		if(userDao.findByMail(user.getMail()) != null)
			return new ResponseManager(406, "Existing mail").getResponse();
		user.setPassword(SecurityManager.encode(user.getPassword()));
		userDao.save(user);
		return new ResponseManager(201, "User registrated").getResponse();
	}

	@Override
	public UserDto getUserByToken(String token)
	{
		User user = userDao.findByAuthToken(token);
		if(user == null)
			return new UserDto();
		user.setPassword(SecurityManager.decode(user.getPassword()));
		return mapper.map(user, UserDto.class);
	}

	@Override
	public ObjectNode updateUserData(User user, String token)
	{
		User existing = userDao.findByAuthToken(token);
		if(existing == null)
			return new ResponseManager(401, "Not Authorized").getResponse();
		existing.setName(user.getName());
		existing.setLastname(user.getLastname());
		// existing.setMail(user.getMail());
		existing.setPassword(SecurityManager.encode(user.getPassword()));
		userDao.save(existing);
		return new ResponseManager(202, "User data updated").getResponse();
	}

	@Override
	public ObjectNode deleteUser(String token)
	{
		User existing = userDao.findByAuthToken(token);
		if(existing == null)
			return new ResponseManager(401, "Not Authorized").getResponse();
		userDao.delete(existing);
		return new ResponseManager(202, "User profile removed").getResponse();
	}

	@Override
	public List<UserDto> getUsers()
	{
		List<User> users = (List<User>) userDao.findAll();
		List<UserDto> usersDto = new ArrayList<>();
		users.forEach(u -> usersDto.add(mapper.map(u, UserDto.class)));
		return usersDto;
	}

	@Override
	public ObjectNode userLoginCheck(User user)
	{
		User existing = userDao.findByMailAndPassword(user.getMail(), SecurityManager.encode(user.getPassword()));
		if(existing == null)
			return new ResponseManager(401, "Not Authorized").getResponse();
		existing.setAuthToken(SecurityManager.generateToken(existing.getMail()));
		userDao.save(existing);
		return new ResponseManager(202, existing.getAuthToken()).getResponse();
	}

	@Override
	public ObjectNode userLogout(String token)
	{
		User user = userDao.findByAuthToken(token);
		if(user == null)
			return new ResponseManager(401, "Not Authorized").getResponse();
		user.setAuthToken(null);
		userDao.save(user);
		return new ResponseManager(202, "Logout Accepted").getResponse();
	}
}