package org.spring.boot.freemark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
 * @author zhangxf
 *
 */
@SpringBootApplication
public class AppStart extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer
{
    public static void main( String[] args )
    {
        SpringApplication.run(AppStart.class, args);
    }

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(9998);
		
	}
}
