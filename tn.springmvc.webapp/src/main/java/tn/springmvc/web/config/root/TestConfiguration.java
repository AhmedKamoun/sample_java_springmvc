package tn.springmvc.web.config.root;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tn.springmvc.bl.init.TestDataInitializer;

/**
 *
 * Integration testing specific configuration - creates a in-memory datasource,
 * sets hibernate on create drop mode and inserts some test data on the
 * database.
 *
 * This allows to clone the project repository and start a running application
 * with the command
 *
 * mvn clean install tomcat7:run-war -Dspring.profiles.active=test
 *
 * Access http://localhost:8080/ and login with test123 / Password2, in order to
 * see some test data, or create a new user.
 *
 */

@Profile("test")
@EnableTransactionManagement
@Configuration
@EnableJpaRepositories(basePackages = { "tn.springmvc.dal" })
public class TestConfiguration {

	@Bean(initMethod = "init")
	public TestDataInitializer initTestData() {
		return new TestDataInitializer();
	}

	@Bean(name = "datasource")
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(org.hsqldb.jdbcDriver.class.getName());
		dataSource.setUrl("jdbc:hsqldb:mem:mydb");
		dataSource.setUsername("sa");
		dataSource.setPassword("jdbc:hsqldb:mem:mydb");
		return dataSource;
	}

	@Bean(name = "entityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DriverManagerDataSource dataSource) {

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		// TODO add dom module to pack to scan
		entityManagerFactoryBean.setPackagesToScan(new String[] {"tn.charity.v2.dom" });
		entityManagerFactoryBean.setLoadTimeWeaver(new InstrumentationLoadTimeWeaver());
		HibernateJpaVendorAdapter jpa_vendor = new HibernateJpaVendorAdapter();
		jpa_vendor.setShowSql(true);
		jpa_vendor.setGenerateDdl(true);
		entityManagerFactoryBean.setJpaVendorAdapter(jpa_vendor);

		Map<String, Object> jpaProperties = new HashMap<String, Object>();
		jpaProperties.put("hibernate.hbm2ddl.auto", "create");
		jpaProperties.put("hibernate.show_sql", "true");
		jpaProperties.put("hibernate.format_sql", "true");
		jpaProperties.put("hibernate.use_sql_comments", "true");
		entityManagerFactoryBean.setJpaPropertyMap(jpaProperties);

		return entityManagerFactoryBean;
	}

}
