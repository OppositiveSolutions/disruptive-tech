package com.careerfocus.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig {


    @Autowired
    DataSource dataSource;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.careerfocus.entity", "com.careerfocus.service");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        properties.setProperty("hibernate.jdbc.batch_size", "20");
        properties.setProperty("hibernate.id.new_generator_mappings", "false");

        // second level cache configurations
//		addLevel2Cache(properties);
        return properties;
    }


//	private void addLevel2Cache(Properties properties){
//		properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.infinispan.InfinispanRegionFactory");
//		properties.setProperty("hibernate.cache.infinispan.cachemanager","java:CacheManager/Employee");
//		properties.setProperty("hibernate.use_second_level_cache", "true");
//		properties.setProperty("hibernate.cache.use_query_cache", "true");
//		properties.setProperty("javax.persistence.sharedCache.mode", "ENABLE_SELECTIVE");
//		properties.setProperty("javax.persistence.CacheRetrieveMode", "USE");
//		properties.setProperty("javax.persistence.storeMode", "USE");
//
//		properties.setProperty("hibernate.naming-strategy", "org.springframework.boot.orm.jpa.SpringNamingStrategy");
////		properties.setProperty("hibernate.generate_statistics", "true");
//		properties.setProperty("hibernate.transaction.manager_lookup_class", "org.infinispan.transaction.lookup.GenericTransactionManagerLookup");
//	}

}
