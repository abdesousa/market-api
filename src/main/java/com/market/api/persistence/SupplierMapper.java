package com.market.api.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.market.api.model.Supplier;

/**
 * 
 * @author Alex Sousa (abdesousa@gmail.com)
 *
 */
public class SupplierMapper implements ResultSetMapper<Supplier> {
	private static final String ID = "supplier_id";
	private static final String NAME = "supplier_nm";

	public Supplier map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
		return new Supplier(resultSet.getInt(ID), resultSet.getString(NAME));

	}
}
