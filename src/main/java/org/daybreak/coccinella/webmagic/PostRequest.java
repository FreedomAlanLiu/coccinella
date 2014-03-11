/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daybreak.coccinella.webmagic;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import us.codecraft.webmagic.Request;

/**
 *
 * @author Alan
 */
public class PostRequest extends Request {

    private final List<NameValuePair> params = new ArrayList<>();

    private String encode = "utf-8";
    
    public PostRequest() {
        super();
    }
    
    public PostRequest(String url) {
        super(url);
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }

    public void addParam(String name, String value) {
        params.add(new BasicNameValuePair(name, value));
    }

    public List<NameValuePair> getParams() {
        return params;
    }

    public HttpEntity createEntity() throws UnsupportedEncodingException {
        return new UrlEncodedFormEntity(getParams(), getEncode());
    }
}
