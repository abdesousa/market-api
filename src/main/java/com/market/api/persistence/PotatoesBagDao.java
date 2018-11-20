package com.market.api.persistence;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import com.market.api.model.PotatoesBag;

/**
 * DAO class to add the persistence methods.
 * 
 * @author Alex Sousa (abdesousa@gmail.com)
 * @since Nov 2018
 *
 */

@RegisterMapper(PotatoesBagMapper.class)
public interface PotatoesBagDao {

	@SqlUpdate("create table potatoes_bag (bag_id varchar(50) primary key, potatoes_qt int, supplier_id int, packed_dt date, price_vl decimal(10,2));")
	public void createTablePotatoesBag();

	@SqlUpdate("drop table potatoes_bag;")
	public void dropTablePotatoesBag();
	
	@SqlUpdate("delete from potatoes_bag;")
	public void deleteTablePotatoesBag();

	@SqlQuery("select bag_id, potatoes_qt, supplier_id, packed_dt, price_vl from potatoes_bag LIMIT :limit;")
	public List<PotatoesBag> listPotatoesBag(@Bind("limit") final int limit);

	@SqlUpdate("insert into potatoes_bag(bag_id, potatoes_qt, supplier_id, packed_dt, price_vl) "
			+ "values(:id, :potatoesQuantity, :supplierId, :packedDate, :priceValue);")
	public void createPotatoesBag(@BindBean final PotatoesBag potatoesBag);

	@SqlQuery("select max(bag_id) bag_id, potatoes_qt, supplier_id, packed_dt, price_vl from potatoes_bag")
	public int lastInsertId();

	@SqlQuery("select bag_id, potatoes_qt, supplier_id, packed_dt, price_vl from potatoes_bag where bag_id =:id;")
	public PotatoesBag getPotatoesBag(@Bind("id") final Long id);

}