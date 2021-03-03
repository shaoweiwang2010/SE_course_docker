package com.in28minutes.rest.webservices.restfulwebservices.helloworld;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.util.zip.GZIPInputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		//return new HelloWorldBean(String.format("Hello World, %s", name));
	// collect questions from Stack Overflow using API
		String url = "https://api.stackexchange.com/2.2/search?order=desc&sort=activity&tagged="+name+"&site=stackoverflow";
			
		//System.out.println(GetContentHttpRequest.getResponseContent(url));
		
		String content = GetContentHttpRequest.getResponseContent(url);
		
		// TODO:  extract post information from content, which is json format
		
		
		
		return new HelloWorldBean(content);
	
	}
	
	
	
}
