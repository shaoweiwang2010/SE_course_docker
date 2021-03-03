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

public class GetContentHttpRequest {

  public static void main(String[] args) {
	  String name = "java";
	  String url = "https://api.stackexchange.com/2.2/search?order=desc&sort=activity&tagged="+name+"&site=stackoverflow";
		
	  System.out.println(getResponseContent(url));
  }
  public static String getResponseContent(String url) {
	  HttpClient client = new HttpClient();

	    HttpMethod method = new GetMethod(url);
	     method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
	            new DefaultHttpMethodRetryHandler(3, false));
	     method.addRequestHeader("Accept-Encoding", "gzip");
	     System.out.println("Setting gzip header explicitiy");
	     
	        try {
	          int statusCode = client.executeMethod(method);

	          if (statusCode != HttpStatus.SC_OK) {
	            System.err.println("Method failed: " + method.getStatusLine());
	          }
	          Header[] responseHeader = method.getResponseHeaders();
	         // for(int i = 0 ; i < responseHeader.length ;i++){
	         //   Header header = responseHeader[i];
	         //   System.out.println(header.getName() +" " + header.getValue());
	         // }
	          String responseBody  = GetContentHttpRequest.getResponseBody(method);
	          
	          return responseBody;

	        } catch (HttpException e) {
	          System.err.println("Fatal protocol violation: " + e.getMessage());
	          e.printStackTrace();
	        } catch (IOException e) {
	          System.err.println("Fatal transport error: " + e.getMessage());
	          e.printStackTrace();
	        } finally {
	          // Release the connection.
	          method.releaseConnection();
	        }  
	      return "error";
  }
  
  

  public static String getResponseBody(HttpMethod method) throws IOException{
    Header contentEncoding = method.getResponseHeader("Content-Encoding");
    System.out.println("Value of Content-encoding header " + contentEncoding);
    if(contentEncoding !=  null ){
      String acceptEncodingValue = contentEncoding.getValue();
      if(acceptEncodingValue.indexOf("gzip") != -1){
      System.out.println("This is gzipped content  " );
      StringWriter responseBody = new StringWriter();
      PrintWriter responseWriter = new PrintWriter(responseBody);
      GZIPInputStream zippedInputStream =  new GZIPInputStream(method.getResponseBodyAsStream());
        BufferedReader r = new BufferedReader(new InputStreamReader(zippedInputStream));
        String line = null;
          while( (line =r.readLine()) != null){
            responseWriter.println(line);
         }
          return responseBody.toString();
      }
    }
    System.out.println("The response is not zipped");
    return method.getResponseBodyAsString();
  }
}
