package com.spring.boot;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.PersistenceExceptionTranslator;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoFactoryBean;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;

@SpringBootApplication
// 如果不在根目录下则需要设置
//@ComponentScan(value="com.spring.boot.*")
// 启动事务管理
@EnableTransactionManagement
@EnableJpaRepositories
//@EnableWebMvc
public class ApplicationStart extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer, TransactionManagementConfigurer{
	private Logger logger = Logger.getLogger(ApplicationStart.class.getName());
	@Value("${spring.datasource.driver-class-name}")
	private String driverClass;
	@Value("${spring.datasource.url}")
	private String jdbcUrl;
	@Value("${spring.datasource.username}")
	private String username;
	@Value("${spring.datasource.password}")
	private String password;
	
//	@Resource(name="tx1")
//	private PlatformTransactionManager txManager;
	
	@Bean
	public ServletRegistrationBean druidServlet(){
		return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
	}
	
	@Bean
	public DataSource getDs(){
		
		logger.info(String.format("获取的数据信息为:%s", driverClass));
		DruidDataSource dds = new DruidDataSource();
		dds.setDriverClassName(driverClass);
		dds.setUrl(jdbcUrl);
		dds.setUsername(username);
		dds.setPassword(password);
		try {
			dds.setFilters("stat,wall");
		} catch (SQLException e) {
			logger.info(String.format("设置状态失败,%s!", e.getMessage()));
		}
		return dds;
	}

// 若果多事务则采用上面，注意本例子中entityManagerFactory 中没有和mongo绑定的
//	@Bean(name="tx1")
//	public PlatformTransactionManager txManager(DataSource dataSource){
//		return new DataSourceTransactionManager(dataSource);
//	}
//	
//	@Bean(name="tx2")
//	public PlatformTransactionManager txManager1(EntityManagerFactory entityManager){
//		return new JpaTransactionManager(entityManager);
//	}

//  已经废弃了
//	@Bean
//	public MongoFactoryBean MongoFactoryBean(){
//		MongoFactoryBean mongo = new MongoFactoryBean();
//		mongo.setHost("localhost");
//		mongo.setPort(27017);
//		return mongo;
//	}
//  已经废弃了
//	@Bean
//	public MongoDbFactory mongoDbFactory() throws MongoException, UnknownHostException{
//		return new SimpleMongoDbFactory(new MongoURI("mongodb://10.252.128.30 :27017/goodlawyer"));
//	}
	
	@Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
	
	public static void main(String[] args) {
		
		SpringApplication.run(ApplicationStart.class, args);
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer arg0) {
		arg0.setPort(9900);
		
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		// TODO Auto-generated method stub
		//return txManager;
		return null;
	}

}
