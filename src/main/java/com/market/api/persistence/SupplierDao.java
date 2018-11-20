package com.market.api.persistence;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.market.api.model.Supplier;

/**
 * DAO class to add the persistence methods.
 * 
 * @author Alex Sousa (abdesousa@gmail.com)
 * @since Nov 2018
 *
 */

@RegisterMapper(SupplierMapper.class)
public interface SupplierDao {
	
  @SqlUpdate("create table supplier(supplier_id int primary key, supplier_nm varchar(100));")
  public void createSupplierTable();
  
  
  @SqlUpdate("insert into supplier(supplier_id,supplier_nm) "
	  		+ "values(:id, :name);")
  public void createSupplier(@BindBean Supplier supplier);

 
}