package it.corso.dto;

public class UserOrderProductDto
{
	private int id;
	private String name;
	private double price;
	private UserOrderProductCategoryDto category;
	
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
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public UserOrderProductCategoryDto getCategory()
	{
		return category;
	}
	public void setCategory(UserOrderProductCategoryDto category)
	{
		this.category = category;
	}
}