package com.colak.springtutorial.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TenantFilter implements Filter {

    public static final String PRIVATE_TENANT_HEADER = "X-PrivateTenant";


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String privateTenant = httpServletRequest.getHeader(PRIVATE_TENANT_HEADER);
        if (privateTenant != null) {
            TenantContext.setCurrentTenant(privateTenant);
        }
        chain.doFilter(request, response);
    }
}
