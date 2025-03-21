package com.baomidou.mybatisplus.samples.crud.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.baomidou.mybatisplus.samples.crud.handler.AType;
import com.baomidou.mybatisplus.samples.crud.handler.ATypeHandler;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author miemie
 * @since 2018-08-10
 */
@Configuration
@MapperScan("com.baomidou.mybatisplus.samples.crud.mapper")
public class MybatisPlusConfig {

    @Bean(name = "dataSource", destroyMethod = "close")
    @Primary
    @ConfigurationProperties(prefix = "datasource.main")
    public DataSource dataSource() {
        // 配置文件里的datasource.main.XXX配置项，会自动设置到ds对象的XXX属性上
        DruidDataSource ds = new DruidDataSource();
        ds.setMinIdle(2);
        ds.setMaxActive(200);
        ds.setRemoveAbandoned(false);
        ds.setTestWhileIdle(true);
        ds.setTestOnBorrow(true);
        ds.setTestOnReturn(false);
        ds.setValidationQuery("select 1");
        ds.setName("mainDataSource");
        return ds;
    }

    @Bean
    public ATypeHandler dbEncrypt(@Value("${datasource.key}") String key) {
        ATypeHandler handler = new ATypeHandler();
        return handler;
    }

    @Bean
    @Primary
    public MybatisSqlSessionFactoryBean sqlSessionFactory(@Qualifier("dataSource") DataSource datasource, ATypeHandler aTypeHandler) throws Exception {
        MybatisSqlSessionFactoryBean sessionFactory = new MybatisSqlSessionFactoryBean();
        sessionFactory.setDataSource(datasource);
        // mapper文件地址
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:/mapper/*.xml"));
        // 加解密字段类型
        sessionFactory.setTypeHandlers(aTypeHandler);
        sessionFactory.setTypeAliases(AType.class);
        MybatisConfiguration config = new MybatisConfiguration();
        config.setMapUnderscoreToCamelCase(true);
        sessionFactory.setConfiguration(config);
        return sessionFactory;
    }

}
