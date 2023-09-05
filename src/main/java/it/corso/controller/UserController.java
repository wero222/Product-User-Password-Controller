package it.corso.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.corso.dto.UserDto;
import it.corso.helper.PasswordValidationException;
import it.corso.helper.ResponseManager;
import it.corso.model.User;
import it.corso.service.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController
{
	@Autowired
	private UserService userService;
	
  // endpoint #1: registrazione utente  localhost:8080/api/user/reg
	@PostMapping("/reg")
	public ObjectNode userRegistration(@Valid @RequestBody User user)
	{
		if(!Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,10}", user.getPassword()))
			throw new PasswordValidationException();
		return userService.userRegistration(user);
	}
	
	// endpoint #2: restituzione dati di un utente (identificato mediante token)  localhost:8080/api/user/get/{user token}
	@GetMapping("/get/{tkn}")
	public UserDto getUserByToken(@PathVariable("tkn") String token)
	{
		return userService.getUserByToken(token);
	}
	
	// endpoint #3: restituzione dati di tutti gli utenti (admin)  localhost:8080/api/user/get/all
	@GetMapping("/get/all")
	public List<UserDto> getUsers()
	{
		return userService.getUsers();
	}
	
	// endpoint #4: aggiornamento dati di un utente  localhost:8080/api/user/update/{user token}
	@PutMapping("/update/{tkn}")
	public ObjectNode updateUserData(@Valid @RequestBody User user, @PathVariable("tkn") String token)
	{
		if(!Pattern.matches("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,10}", user.getPassword()))
			throw new PasswordValidationException();
		return userService.updateUserData(user, token);
	}
	
	// endpoint #5: cancellazione profilo utente  localhost:8080/api/user/delete/{user token}
	@DeleteMapping("/delete/{tkn}")
	public ObjectNode deleteUser(@PathVariable("tkn") String token)
	{
		return userService.deleteUser(token);
	}
	
	// endpoint #6: login utente  localhost:8080/api/user/login
	@PutMapping("/login")
	public ObjectNode userLoginCheck(@RequestBody User user)
	{
		return userService.userLoginCheck(user);
	}
	
	// endpoint #7: logout utente  localhost:8080/api/user/logout/{user token}
	@GetMapping("/logout/{tkn}")
	public ObjectNode userLogout(@PathVariable("tkn") String token)
	{
		return userService.userLogout(token);
	}
	
	// gestione eccezione di validazione dati
	@ExceptionHandler(BindException.class)
	public ResponseEntity<Map<String, String>> handleValidationException(BindException e)
	{
		Map<String, String> errors = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.badRequest().body(errors);
	}
	
	// gestione eccezione di validazione password
	@ExceptionHandler(PasswordValidationException.class)
	public ObjectNode handleValidationPasswordException(PasswordValidationException e)
	{
		return new ResponseManager(400, e.getMESSAGE()).getResponse();
	}
}