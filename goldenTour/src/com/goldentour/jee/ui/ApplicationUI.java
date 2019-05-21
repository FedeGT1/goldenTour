package com.goldentour.jee.ui;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.goldentour.jee.config.ConfigurationBean;
import com.goldentour.jee.config.CustomServletInitializer;

public class ApplicationUI {
	
	static AbstractApplicationContext  context = null;

	public static void main(String[] args) {

		try {

			// Caricamento del contesto e lettura delle configurazioni.
//			context = new AnnotationConfigApplicationContext(CustomServletInitializer.class);
			context = new AnnotationConfigApplicationContext(ConfigurationBean.class);
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
