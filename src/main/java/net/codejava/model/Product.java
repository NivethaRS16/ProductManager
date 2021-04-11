package net.codejava.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "product")
public class Product {		
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
     
    @Column(length = 64, unique = true, nullable = false)
    private String name;
     
    private float price;
    
    @NotBlank(message = "Enter the brand name")
    private String brand;
    
    @NotBlank(message = "Enter the Made In information")
    private String madein;
     
    public Product(String name, float price) {
        this.name = name;
        this.price = price;
    }
 
    public Product() {
    }
 
    // getters and setters are not shown for brevity...
 
    @Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", brand=" + brand + ", madein=" + madein
				+ "]";
	}

	public Product(String name, String brand, String madein, float price) {
		this.name = name;
		this.price = price;
		this.brand = brand;
		this.madein = madein;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getMadein() {
		return madein;
	}

	public void setMadein(String madein) {
		this.madein = madein;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}      
}