package com.shadowsych.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class PHPConnection {
	public PHPConnection() {
	}
	public String HTTPRequest(String urlRequest, String requestMethod, Map<String,String> requestVariables) {
		if(requestMethod.equalsIgnoreCase("GET")) {
			try {
				//re-format and append variables to the GET URL
				urlRequest += "?";
				Object[] getVariables = requestVariables.entrySet().toArray();
				for(int i = 0; i < requestVariables.size(); i++) {
					Object getVariable = getVariables[i];
					getVariable = getVariable.toString().replaceAll(" ", "%20");
					urlRequest += getVariable + "&";
				}
				
				//establish the new connection
				URL url = new URL(urlRequest);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET");
				
				//read the results and return the output
				BufferedReader outputReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				StringBuffer outputString = new StringBuffer();
				String line;
				while((line = outputReader.readLine()) != null) {
					outputString.append(line);
				}
				outputReader.close();
				return outputString.toString();
			} catch (IOException e) {
				return e.toString();
			}
		} else if(requestMethod.equalsIgnoreCase("POST")) {
			try {
				 //re-format the data for POST variables
				 urlRequest += "?";
				 String data = "";
				 Object[] getVariables = requestVariables.entrySet().toArray();
				 for(int i = 0; i < requestVariables.size(); i++) {
					Object getVariable = getVariables[i];
					getVariable = getVariable.toString().replaceAll(" ", "%20");
					data += getVariable + "&";
			     }
				 
				 //establish the new connection
			     URL url = new URL(urlRequest);
			     HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				 
			     //send data via a POST request
			     conn.setDoOutput(true);
			     conn.setInstanceFollowRedirects(true);
			     conn.setRequestMethod("POST");
			     conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			     conn.setRequestProperty("charset", "UTF-8");
			     conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			     conn.setUseCaches(false);
			     try(OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream()))
			     {
			          out.write(data);
			     } 

			     //read the results and return the output
			     BufferedReader outputReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			     StringBuffer outputString = new StringBuffer();
			     String line;
			     while((line = outputReader.readLine()) != null)
			     {
			    	 outputString.append(line);
			     }
			     outputReader.close();
			     return outputString.toString().replaceAll("<br >", "\n");	
			}  catch (IOException e) {
				return e.toString();
			}
		} else {
			return "shadowsych.httprequest Exception: Please specify your HTTP request as either POST or GET.";
		}
	}
}
