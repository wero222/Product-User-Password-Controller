package it.corso.dto;
import java.time.LocalDate;
import java.util.List;

public class UserOrderDto
{
	private int id;
	private LocalDate date;
	private double amount;
	private List<UserOrderProductDto> products;
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public LocalDate getDate()
	{
		return date;
	}
	public void setDate(LocalDate date)
	{
		this.date = date;
	}
	public double getAmount()
	{
		return amount;
	}
	public void setAmount(double amount)
	{
		this.amount = amount;
	}
	public List<UserOrderProductDto> getProducts()
	{
		return products;
	}
	public void setProducts(List<UserOrderProductDto> products)
	{
		this.products = products;
	}
}