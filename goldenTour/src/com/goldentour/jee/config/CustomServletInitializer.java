package com.goldentour.jee.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class CustomServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
    protected Class<?>[] getRootConfigClasses() {
		return new Class[]{WebMvcConfig.class};
    }

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[]{"/"};
	}

    @Override
    protected Filter[] getServletFilters() {
		Filter[] singleton;
		singleton = new Filter[]{new CORSFilter()};
		return singleton;
    }

}
