package com.market.api.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import com.market.api.model.PotatoesBag;

/**
 * Maps the SQL result sets to a model, which we do by registering a mapper class.
 * @author Alex Sousa (abdesousa@gmail.com)
 *
 */
public class PotatoesBagMapper implements ResultSetMapper<PotatoesBag> {
	private static final String ID = "BAG_id";
	private static final String POTATOES_QTY = "potatoes_qt";
	private static final String SUPPLIER_ID = "supplier_id";
	private static final String PACKED_DATE = "packed_dt";
	private static final String PRICE_VALUE = "price_vl";

	public PotatoesBag map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
		return new PotatoesBag(resultSet.getString(ID), resultSet.getInt(POTATOES_QTY),
				resultSet.getInt(SUPPLIER_ID), resultSet.getDate(PACKED_DATE),
				resultSet.getBigDecimal(PRICE_VALUE));

	}
}
