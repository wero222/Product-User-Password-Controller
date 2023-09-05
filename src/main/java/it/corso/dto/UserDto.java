package it.corso.dto;
import java.util.List;

public class UserDto
{
	private int id;
	private String name;
	private String lastname;
	private String mail;
	private String password;
	private String authToken;
	private List<UserOrderDto> orders;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getLastname()
	{
		return lastname;
	}
	public void setLastname(String lastname)
	{
		this.lastname = lastname;
	}
	public String getMail()
	{
		return mail;
	}
	public void setMail(String mail)
	{
		this.mail = mail;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public String getAuthToken()
	{
		return authToken;
	}
	public void setAuthToken(String authToken)
	{
		this.authToken = authToken;
	}
	public List<UserOrderDto> getOrders()
	{
		return orders;
	}
	public void setOrders(List<UserOrderDto> orders)
	{
		this.orders = orders;
	}
}