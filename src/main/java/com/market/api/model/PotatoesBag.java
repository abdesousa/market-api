package com.market.api.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Entity class for bag of potatoes.
 * 
 * @author Alex Sousa (abdesousa@gmail.com)
 * @since Nov, 2018
 */

public class PotatoesBag {

	private String id;
	private Integer potatoesQuantity;
	private Date packedDate;
	private BigDecimal priceValue;
	private Integer supplierId;

	public PotatoesBag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PotatoesBag(String id, Integer potatoesQuantity, Integer supplierId, Date packedDate, BigDecimal priceValue) {
		super();
		this.id = id;
		this.potatoesQuantity = potatoesQuantity;
		this.supplierId = supplierId;
		this.packedDate = packedDate;
		this.priceValue = priceValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getPotatoesQuantity() {
		return potatoesQuantity;
	}

	public void setPotatoesQuantity(Integer potatoesQuantity) {
		this.potatoesQuantity = potatoesQuantity;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplier(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public Date getPackedDate() {
		return packedDate;
	}

	public void setPackedDate(Date packedDate) {
		this.packedDate = packedDate;
	}

	public BigDecimal getPriceValue() {
		return priceValue;
	}

	public void setPriceValue(BigDecimal priceValue) {
		this.priceValue = priceValue;
	}

}
