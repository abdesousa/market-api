package com.market.api.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.skife.jdbi.v2.exceptions.UnableToExecuteStatementException;
import org.skife.jdbi.v2.exceptions.UnableToObtainConnectionException;
import org.skife.jdbi.v2.sqlobject.CreateSqlObject;

import com.market.api.model.PotatoesBag;
import com.market.api.model.Supplier;
import com.market.api.persistence.PotatoesBagDao;
import com.market.api.persistence.SupplierDao;

/**
 * Class in charge of the business logic to handle persistence objects and apply
 * business rules.
 * 
 * @author Alex Sousa (abdesousa@gmail.com)
 */
public abstract class PotatoesBagService {

	private static final String DATABASE_REACH_ERROR = "Could not reach the MySQL database. The database may be down or there may be network connectivity issues. Details: ";
	private static final String DATABASE_CONNECTION_ERROR = "Could not create a connection to the MySQL database. The database configurations are likely incorrect. Details: ";
	private static final String DATABASE_UNEXPECTED_ERROR = "Unexpected error occurred while attempting to reach the database. Details: ";
	private static final String PRICE_VL_ERROR = "The price of a Potatoes Bag must be between 1 and 50.";
	private static final String POTATOES_QTY_ERROR = "The quantity of potatoes must be between 1 and 100.";

	
	@CreateSqlObject
	abstract PotatoesBagDao potatoesBagDao();

	@CreateSqlObject
	abstract SupplierDao supplierDao();

	/**
	 * Auto generate the UUID.
	 * 
	 * @return
	 */
	private static String generatePK() {
		UUID uuid = UUID.randomUUID();

		return uuid.toString();
	}

	/**
	 * Method to list the available Potatoes Bags.
	 * 
	 * @param limit max quantity of returned items.
	 * @return
	 */
	public List<PotatoesBag> list(Integer limit) {

		if (limit != null) {
			if (limit.intValue() > 100) {
				limit = 100;
			} 
		} else {
			limit = 3;
		}

		return potatoesBagDao().listPotatoesBag(limit);
	}

	/**
	 * Create a new potatoes bag.
	 * 
	 * @param potatoesBag
	 * @return
	 */
	public PotatoesBag create(PotatoesBag potatoesBag) {

		potatoesBag.setId(generatePK()); // auto generate the ID.

		if ((potatoesBag.getPotatoesQuantity().intValue()>=1 && potatoesBag.getPotatoesQuantity().intValue()<=100)) {
			potatoesBag.setId(generatePK()); // auto generate the ID.
			potatoesBagDao().createPotatoesBag(potatoesBag);
		} else {
			throw new WebApplicationException(String.format(POTATOES_QTY_ERROR, potatoesBag.getId()), Status.INTERNAL_SERVER_ERROR);
		}
		
		if ((potatoesBag.getPriceValue().compareTo(new BigDecimal(1)) >= 0)
				&& (potatoesBag.getPriceValue().compareTo(new BigDecimal(50)) <= 0)) {
			potatoesBag.setId(generatePK()); // auto generate the ID.
			potatoesBagDao().createPotatoesBag(potatoesBag);
		} else {
			throw new WebApplicationException(String.format(PRICE_VL_ERROR, potatoesBag.getId()), Status.INTERNAL_SERVER_ERROR);
		}


		return potatoesBag;

	}

	/**
	 * Create the database strucuture and populate the supplier database.
	 */
	public void createTables() {

		try {
			supplierDao().createSupplierTable();
		} catch (Exception e) {

		}

		try {

			supplierDao().createSupplier(new Supplier(1, "De Coster"));
			supplierDao().createSupplier(new Supplier(2, "Patatas Ruben"));
			supplierDao().createSupplier(new Supplier(3, "Yunnan Spices"));
		} catch (Exception e) {

		}
		try {
			potatoesBagDao().createTablePotatoesBag();
		} catch (Exception e) {
			potatoesBagDao().dropTablePotatoesBag();
			potatoesBagDao().createTablePotatoesBag();

		}

	}

	/**
	 * Execute a health check.
	 * 
	 * @return
	 */
	public String performHealthCheck() {
		try {
			potatoesBagDao().listPotatoesBag(1);
		} catch (UnableToObtainConnectionException ex) {
			return checkUnableToObtainConnectionException(ex);
		} catch (UnableToExecuteStatementException ex) {
			return checkUnableToExecuteStatementException(ex);
		} catch (Exception ex) {
			return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
		}
		return null;
	}

	/**
	 * Check for unavailability of connection.
	 * 
	 * @param ex
	 * @return
	 */
	private String checkUnableToObtainConnectionException(UnableToObtainConnectionException ex) {
		if (ex.getCause() instanceof java.sql.SQLNonTransientConnectionException) {
			return DATABASE_REACH_ERROR + ex.getCause().getLocalizedMessage();
		} else if (ex.getCause() instanceof java.sql.SQLException) {
			return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
		} else {
			return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
		}
	}

	/**
	 * Check for unable to execute queries.
	 * 
	 * @param ex
	 * @return
	 */
	private String checkUnableToExecuteStatementException(UnableToExecuteStatementException ex) {
		if (ex.getCause() instanceof java.sql.SQLSyntaxErrorException) {
			return DATABASE_CONNECTION_ERROR + ex.getCause().getLocalizedMessage();
		} else {
			return DATABASE_UNEXPECTED_ERROR + ex.getCause().getLocalizedMessage();
		}
	}
}