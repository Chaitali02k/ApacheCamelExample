package com.apachecamel.multipleroutes;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class MultipleRoutes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CamelContext context=new DefaultCamelContext();
		try {
			context.addRoutes(new RouteBuilder() {
				
				@Override
				public void configure() throws Exception {
					// TODO Auto-generated method stub
					from("file:data/input?noop=true")
					.to("log:?level=INFO&showBody=true&showHeaders=true")
					.to("file:data/output")
					.to("file:data/outputcopy");
					
					from("file:data/input1?noop=true")
					.to("log:?level=INFO&showBody=true&showHeaders=true")
					.to("file:data/output1");
				}
			});
			context.start();
			Thread.sleep(5000);
			context.stop();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
