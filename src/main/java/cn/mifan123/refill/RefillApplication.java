package cn.mifan123.refill;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableTransactionManagement
@EnableSwagger2Doc
@SpringBootApplication
public class RefillApplication {

	public static void main(String[] args) {
		SpringApplication.run(RefillApplication.class, args);
	}
}
