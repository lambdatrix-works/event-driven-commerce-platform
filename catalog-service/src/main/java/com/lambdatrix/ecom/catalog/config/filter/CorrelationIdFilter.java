package com.lambdatrix.ecom.catalog.config.filter;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.lambdatrix.ecom.common.api.headers.HeaderNames;
import com.lambdatrix.ecom.common.util.IDs;

public class CorrelationIdFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String correlationId = IDs.correlationId();
    response.setHeader(HeaderNames.CORRELATION_ID, correlationId);
    filterChain.doFilter(request, response);
  }
}
