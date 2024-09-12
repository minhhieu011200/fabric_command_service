package com.example.demo.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class RequestResponseLoggingFilter implements Filter {
	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		log.info("Initializing filter :{}", this);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		String ipAddress = getIpAddress(req);
		req.setAttribute(ipAddress, ipAddress);


		log.info("Method: " + req.getMethod() + ": " + req.getRequestURI() + " ipAddress: " + ipAddress + " body: "
				+ req.getParameterMap().toString());
		chain.doFilter(req, response);

	}

	public String getIpAddress(HttpServletRequest req) {
		String ipAddress = req.getHeader("X-Forwarded-For");
		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = req.getHeader("X-Real-IP");
		}
		if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = req.getRemoteAddr();
		}

		if (ipAddress != null && ipAddress.contains(",")) {
			ipAddress = ipAddress.split(",")[0].trim();
		}
		return ipAddress;
	}
}
