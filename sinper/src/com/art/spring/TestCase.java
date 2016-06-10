package com.art.spring;

import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;

public class TestCase {

    public void testInitContext() {
	String conf = "spring-mvc.xml";
	ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
	
	HandlerMapping hm = ac.getBean("handerMapping", HandlerMapping.class);
	Properties properties = ac.getBean("urlMappings", Properties.class);
	ViewResolver tViewResolver = ac.getBean("jspViewResolver", ViewResolver.class);
	System.out.println(hm);
	System.out.println(properties);
	System.out.println(tViewResolver);
    }

    public static void main(String[] args) {
	new TestCase().testInitContext();
    }
}
