package com.withapachecamel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class WithApacheCamel {

	public static void main(String[] args) {
		// Initialize the Camel Context
		CamelContext context = new DefaultCamelContext();

		try {
			context.addRoutes(new RouteBuilder() {
				@Override
				public void configure() throws Exception {
					// URI: component:file Context Path
					from("file:data/input?noop=true").to("file:data/output");
				}
			});

			context.start();
			Thread.sleep(5000);
			context.stop();

		} catch (Exception e) {
			System.out.println("Inside Exception : " + e);
		}

	}

}
