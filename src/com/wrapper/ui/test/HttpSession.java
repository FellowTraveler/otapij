/*
*To change this template, choose Tools | Templates
*and open the template in the editor.
 */

package com.wrapper.ui.test;

import java.io.IOException;
import java.net.URI;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import ru.paradoxs.bitcoin.http.exceptions.HttpSessionException;

public class HttpSession
{
  private static final String JSON_CONTENT_TYPE = "application/json";
  private static final String POST_CONTENT_TYPE = "text/plain";
  private HttpClient client = null;
  private URI uri = null;
  private Credentials credentials = null;

  public HttpSession(URI uri, Credentials credentials) {
    this.uri = uri;
    this.credentials = credentials;
  }

  public JSONObject sendAndReceive(JSONObject message) {
    PostMethod method = new PostMethod(this.uri.toString());
    JSONObject localJSONObjectbb=null;
    try
    {
        System.out.println("1");
      method.setRequestHeader("Content-Type", "text/plain");
System.out.println("1x");
      RequestEntity requestEntity = new StringRequestEntity(message.toString(), "application/json", null);
      method.setRequestEntity(requestEntity);
System.out.println("1dff");
      HttpClient hh = getHttpClient();
     hh.setTimeout(100000);
      System.out.println("hh:");
    
       hh.executeMethod(method);
     
      System.out.println("222");

      int statusCode = method.getStatusCode();
System.out.println("1dddddddd");
      if (statusCode != 200) {
        throw new HttpSessionException("HTTP Status - " + HttpStatus.getStatusText(statusCode) + " (" + statusCode + ")");
      }

      JSONTokener tokener = new JSONTokener(method.getResponseBodyAsString());
      Object rawResponseMessage = tokener.nextValue();
      JSONObject response = (JSONObject)rawResponseMessage;

      if (response == null) {
        throw new HttpSessionException("Invalid response type");
      }

      JSONObject localJSONObject1 = response;
localJSONObjectbb = response;
      return localJSONObject1; } catch (HttpException e) { } catch (IOException e) { } catch (JSONException e) { } finally { method.releaseConnection();
       return localJSONObjectbb;
    }
  }

  private HttpClient getHttpClient() {
    if (this.client == null) {
      this.client = new HttpClient();
      this.client.getState().setCredentials("jsonrpc", "localhost", this.credentials);
      //this.client.getState().setCredentials(AuthScope.ANY, this.credentials);
    }
    System.out.println("getxxx");
    return this.client;
  }
}
