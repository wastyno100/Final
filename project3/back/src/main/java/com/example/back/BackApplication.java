package com.example.back;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@SpringBootApplication
public class BackApplication {

	public static void main(String[] args) {

		SpringApplication.run(BackApplication.class, args);
	}
//	@Bean
//	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
//
//		//sqlSessionFactory 객체 생성 후 DataSource를
//		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//		sessionFactory.setDataSource(dataSource);
//
//		//Resource에 sql를 입력한 xml 파일의 위치정보를 입력
//		//여러 파일을 설정하기 위해 배열을 사용
//		//여러 파일을 설정하기 위해 *.xml로 설정
//		Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml");
//		sessionFactory.setMapperLocations(res);
//
//		//return값은 DB 연결 객체를 오브젝트로 반환
//		return sessionFactory.getObject();
//	}

}


