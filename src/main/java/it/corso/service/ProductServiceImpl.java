package it.corso.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.corso.dao.CategoryDao;
import it.corso.dao.ProductDao;
import it.corso.dto.ProductDto;
import it.corso.helper.ResponseManager;
import it.corso.model.Category;
import it.corso.model.Product;

@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ProductDao productDao;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<ProductDto> getProducts()
	{
		List<Product> products = (List<Product>) productDao.findAll();
		List<ProductDto> productsDto = new ArrayList<>();
		products.forEach(p -> productsDto.add(mapper.map(p, ProductDto.class)));
		return productsDto;
	}

	@Override
	public ObjectNode registerProduct(Product product)
	{
		Optional<Category> categoryOptional = categoryDao.findById(product.getCategory().getId());
		if(!categoryOptional.isPresent())
			return new ResponseManager(404, "Category not found").getResponse();
		product.setCategory(categoryOptional.get());
		productDao.save(product);
		return new ResponseManager(201, "Product registrated").getResponse();
	}

	@Override
	public ObjectNode updateProduct(Product product)
	{
		Optional<Product> productOptional = productDao.findById(product.getId());
		if(!productOptional.isPresent())
			return new ResponseManager(404, "Product not found").getResponse();
		Optional<Category> categoryOptional = categoryDao.findById(product.getCategory().getId());
		if(!categoryOptional.isPresent())
			return new ResponseManager(404, "Category not found").getResponse();
		Product existing = productOptional.get();
		existing.setName(product.getName());
		existing.setPrice(product.getPrice());
		existing.setCategory(categoryOptional.get());
		productDao.save(existing);
		return new ResponseManager(202, "Product data updated").getResponse();
	}

	@Override
	public ObjectNode deleteProduct(int id)
	{
		Optional<Product> productOptional = productDao.findById(id);
		if(!productOptional.isPresent())
			return new ResponseManager(404, "Product not found").getResponse();
		productDao.delete(productOptional.get());
		return new ResponseManager(202, "Product removed").getResponse();
	}
}