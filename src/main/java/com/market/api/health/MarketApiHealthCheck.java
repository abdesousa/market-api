package com.market.api.health;

import com.codahale.metrics.health.HealthCheck;
import com.market.api.service.PotatoesBagService;

/**
 * This is responsible to check if the database is up and running before saying
 * that our service is healthy.
 * 
 * @author Alex Sousa (abdesousa@gmail.com)
 */
public class MarketApiHealthCheck extends HealthCheck {

	private static final String HEALTHY = "The Market API Service is healthy for read and write";
	private static final String UNHEALTHY = "The Market API Service is not healthy. ";
	private static final String MESSAGE_PLACEHOLDER = "{}";

	private final PotatoesBagService service;

	public MarketApiHealthCheck(PotatoesBagService service) {
		this.service = service;
	}

	@Override
	public Result check() throws Exception {
		String mySqlHealthStatus = service.performHealthCheck();

		if (mySqlHealthStatus == null) {
			return Result.healthy(HEALTHY);
		} else {
			return Result.unhealthy(UNHEALTHY + MESSAGE_PLACEHOLDER, mySqlHealthStatus);
		}
	}
}