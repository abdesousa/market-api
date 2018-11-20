package com.market.api;

import javax.sql.DataSource;

import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;
import org.skife.jdbi.v2.DBI;

import com.market.api.auth.MarketApiAuthenticator;
import com.market.api.auth.MarketApiAuthorizer;
import com.market.api.health.MarketApiHealthCheck;
import com.market.api.model.User;
import com.market.api.resource.PotatoesBagResource;
import com.market.api.service.PotatoesBagService;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.oauth.OAuthCredentialAuthFilter;
import io.dropwizard.setup.Environment;

/**
 * This class will bring all the bundles together and bring the application up
 * and get it running for use.
 * 
 * @author Alex Sousa (abdesousa@gmail.com)
 */
public class MarketApiApplication extends Application<MarketApiConfiguration> {

	private static final String SQL = "sql";
	private static final String BEARER = "Bearer";
	private static final String MARKETAPI_SERVICE = "Market API service";

	public static void main(String[] args) throws Exception {
		new MarketApiApplication().run(args);
	}
	
	@Override
	public void run(MarketApiConfiguration configuration, Environment environment) throws Exception {

		// Datasource configuration
		final DataSource dataSource = configuration.getDataSourceFactory().build(environment.metrics(), SQL);
		DBI dbi = new DBI(dataSource);
			
		// Register Health Check
		MarketApiHealthCheck healthCheck = new MarketApiHealthCheck(dbi.onDemand(PotatoesBagService.class));
		environment.healthChecks().register(MARKETAPI_SERVICE, healthCheck);

		// Register OAuth authentication
		environment.jersey()
				.register(new AuthDynamicFeature(
						new OAuthCredentialAuthFilter.Builder<User>().setAuthenticator(new MarketApiAuthenticator())
								.setAuthorizer(new MarketApiAuthorizer()).setPrefix(BEARER).buildAuthFilter()));
		environment.jersey().register(RolesAllowedDynamicFeature.class);

		// Register resources
		environment.jersey().register(new PotatoesBagResource(dbi.onDemand(PotatoesBagService.class)));

	}

}
