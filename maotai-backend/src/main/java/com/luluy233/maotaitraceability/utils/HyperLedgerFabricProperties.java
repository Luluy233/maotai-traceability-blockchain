package com.luluy233.maotaitraceability.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * @author Luluy233
 * @date 2024/5/6
 */
@Configuration
@ConfigurationProperties(prefix = "fabric")
@Data
public class HyperLedgerFabricProperties {

    private String mspId;

    private String networkConnectionConfigPath;

    private String certificatePath;

    private String privateKeyPath;

    private String tlsCertPath;

    private String channel;

}