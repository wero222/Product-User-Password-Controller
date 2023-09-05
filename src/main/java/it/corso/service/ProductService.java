package it.corso.service;
import java.util.List;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.corso.dto.ProductDto;
import it.corso.model.Product;

public interface ProductService
{
	List<ProductDto> getProducts();
	ObjectNode registerProduct(Product product);
	ObjectNode updateProduct(Product product);
	ObjectNode deleteProduct(int id);
}