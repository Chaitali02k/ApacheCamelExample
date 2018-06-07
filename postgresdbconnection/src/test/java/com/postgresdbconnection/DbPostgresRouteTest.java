package com.postgresdbconnection;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

public class DbPostgresRouteTest extends CamelTestSupport {

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new DbPostgresRoute();
	}

	@Override
	public CamelContext createCamelContext() {

		//Connecting to DB
		String url = "jdbc:postgresql://localhost:5432/localdb";
		DataSource dataSource = setupDataSource(url);

		//need to Set DataSource in order to use in the Route
		SimpleRegistry registry = new SimpleRegistry();
		registry.put("myDataSource", dataSource);

		//Passing the registry to The CamelContext
		CamelContext context = new DefaultCamelContext(registry);
		
		return context;
	}

	private static DataSource setupDataSource(String connectURI) {
		BasicDataSource ds = new BasicDataSource();
		ds.setUsername("postgres");
		ds.setDriverClassName("org.postgresql.Driver");
		ds.setPassword("postgres");
		ds.setUrl(connectURI);
		return ds;
	}

	@Test
	public void insertData() {

		String input = "first db input with value1";
		ArrayList responseList = template.requestBody("direct:dbInput", input, ArrayList.class);

	}

}
