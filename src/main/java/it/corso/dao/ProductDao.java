package it.corso.dao;
import org.springframework.data.repository.CrudRepository;
import it.corso.model.Product;

public interface ProductDao extends CrudRepository<Product, Integer>
{

}