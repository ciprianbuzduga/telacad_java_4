/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ro.telacad.clinicamed.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
//@EnableWebMvc
@EnableTransactionManagement
@ComponentScan("ro.telacad.clinicamed.*")
@EnableAspectJAutoProxy
public class ProjectConfig {
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dmds = new DriverManagerDataSource();
        dmds.setUrl("jdbc:mysql://localhost:3306/spital_privat_database");
        dmds.setDriverClassName("com.mysql.jdbc.Driver");
        dmds.setUsername("user");
        dmds.setPassword("12345");
        return dmds;
    }

    @Bean
    @Autowired
    public LocalEntityManagerFactoryBean entityManagerFactory(
            DataSource dataSource) {
        LocalEntityManagerFactoryBean emf = new LocalEntityManagerFactoryBean();
        emf.setPersistenceUnitName("ClinicaMed-PU");
        return emf;
    }

    @Bean
    @Autowired
    public JpaTransactionManager transactionManager(DataSource dataSource,
            EntityManagerFactory emf) {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setDataSource(dataSource);
        tm.setEntityManagerFactory(emf);
        return tm;
    }
}
