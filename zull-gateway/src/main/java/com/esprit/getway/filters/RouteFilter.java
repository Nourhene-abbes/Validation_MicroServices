package com.esprit.getway.filters;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class RouteFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletResponse response = ctx.getResponse();

		System.out.println("using route filter ");
		response.addHeader("X-Sample", UUID.randomUUID().toString());
		return null;
	}

	@Override
	public String filterType() {
		return "route";

	}

	@Override
	public int filterOrder() {
		return 0;
	}
}
