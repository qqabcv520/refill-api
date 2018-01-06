package cn.mifan123.refill.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "common")
public class CommonConfig {

    private String jwtKey = "bWlmYW4=";

    private Integer jwtExpiration = 1000*60*60*24*15;

    private String jMessageAppKey = "";
    private String jMessageSecret = "";
}
