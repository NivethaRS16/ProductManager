package net.codejava.service;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import net.codejava.model.Product;
import net.codejava.repository.ProductRepository;
 
@Service
public class ProductService {
 
    @Autowired
    private ProductRepository repo;
     
    public List<Product> listAll() {
        return (List<Product>) repo.findAll();
    }
     
    public void save(Product product) throws Exception {
    	try {
        repo.save(product);
    	}
    	 catch(DataIntegrityViolationException e) {
    		 throw new Exception("Product with same name already exists!!");
    	 }
    	catch(Exception e) {
    		throw new Exception("An unexpected error occured!! Please try again");
    	}
    }
     
    public Product get(int id) {
        return repo.findById(id).get();
    }
     
    public void delete(int id) {
        repo.deleteById(id);
    }

    public Product find(int id) {
		// TODO Auto-generated method stub
		if(repo.findById(id).isPresent()) {
			return repo.findById(id).get();
		}
		else {
			return null;
		}
	}
    
    public double calculateSum(List<Product> productList) {
		// TODO Auto-generated method stub
    	return productList.stream().collect(Collectors.summingDouble(p->p.getPrice()));
	}
}