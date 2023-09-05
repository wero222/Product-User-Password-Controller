package it.corso.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.corso.dto.ProductDto;
import it.corso.model.Product;
import it.corso.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController
{
	@Autowired
	private ProductService productService;
	
	// endpoint #1: ottenimento elenco prodotti  localhost:8080/api/product/get
	@GetMapping("/get")
	public List<ProductDto> getProducts()
	{
		return productService.getProducts();
	}
	
	// endpoint #2: registrazione prodotto  localhost:8080/api/product/reg
	@PostMapping("/reg")
	public ObjectNode registerProduct(@RequestBody Product product)
	{
		return productService.registerProduct(product);
	}
	
	// endpoint #3: modifica dati prodotto  localhost:8080/api/product/update
	@PutMapping("/update")
	public ObjectNode updateProduct(@RequestBody Product product)
	{
		return productService.updateProduct(product);
	}
	
	// endpoint #4: cancellazione prodotto  localhost:8080/api/product/delete/{product id}
	@DeleteMapping("/delete/{id}")
	public ObjectNode deleteProduct(@PathVariable("id") int id)
	{
		return productService.deleteProduct(id);
	}
}