package com.common;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class RequestFilter implements Filter{ 
	@Override  
    public void destroy() {  
          
    }  
  
    @Override  
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
        ServletRequest requestWrapper = null;    
        if(request instanceof HttpServletRequest) {    
            try {
				requestWrapper = new MAPIHttpServletRequestWrapper((HttpServletRequest) request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
        }    
        if(requestWrapper == null) {    
            chain.doFilter(request, response);    
        } else {    
            chain.doFilter(requestWrapper, response);    
        }    
    }  
  
    @Override  
    public void init(FilterConfig arg0) throws ServletException {  
          
    }  
}
