package cn.mifan123.refill.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "common")
public class CommonConfig {

    @Getter
    @Setter
    private String jwtKey;


    @Getter
    @Setter
    private Integer jwtExpiration = 1000*60*60*24*15;
}
