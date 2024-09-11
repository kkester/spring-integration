package io.spring.client.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class TcpProperties {

    @Value("${tcp.server.host}")
    private String serverHost;

    @Value("${tcp.server.port}")
    private int serverPort;

    @Value("${connection.retry.interval}")
    private int connectionRetryInterval;

}
