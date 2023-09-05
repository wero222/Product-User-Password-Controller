package it.corso.dto;
import java.util.List;

public class ProductDto
{
	private int id;
	private String name;
	private double price;
	private ProductCategoryDto category;
	private List<ProductOrderDto> orders;
	
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
	public ProductCategoryDto getCategory()
	{
		return category;
	}
	public void setCategory(ProductCategoryDto category)
	{
		this.category = category;
	}
	public List<ProductOrderDto> getOrders()
	{
		return orders;
	}
	public void setOrders(List<ProductOrderDto> orders)
	{
		this.orders = orders;
	}
}