package com.market.api.model;

/**
 * Entity class for suppliers.
 * @author Alex Sousa (abdesousa@gmail.com)
 * @since Nov, 2018
 */
public class Supplier {

    private Integer id;
    private String name;

	public Supplier(Integer id) {
		super();
		this.id = id;
	}
	public Supplier(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
}
