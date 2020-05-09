package cn.ekgc.ams;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <b>汽车管理系统启动器</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
@MapperScan("cn.ekgc.ams.dao")
@SpringBootApplication
public class AmsStarter {
	public static void main(String[] args) {
		SpringApplication.run(AmsStarter.class, args);
	}
}
