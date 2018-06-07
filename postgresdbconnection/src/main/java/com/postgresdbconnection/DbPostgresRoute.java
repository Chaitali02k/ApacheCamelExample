package com.postgresdbconnection;

import org.apache.camel.builder.RouteBuilder;

public class DbPostgresRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("direct:dbInput").to("log:?level=INFO&showBody=true").process(new InsertProcessor())
		         //Initialized myDataSource in the CamelContext
				.to("jdbc:myDataSource").to("log:?level=INFO&showBody=true");

	}

}
