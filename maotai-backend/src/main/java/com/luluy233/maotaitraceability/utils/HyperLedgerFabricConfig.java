package com.luluy233.maotaitraceability.utils;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hyperledger.fabric.gateway.*;
import org.hyperledger.fabric.sdk.ChaincodeEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * @author Luluy233
 * @date 2024/5/6
 */
@Configuration
@AllArgsConstructor
@Slf4j
public class HyperLedgerFabricConfig {

    private final HyperLedgerFabricProperties hyperLedgerFabricProperties;

    @Bean
    public Gateway gateway() throws Exception {

        BufferedReader certificateReader = Files.newBufferedReader(Paths.get(hyperLedgerFabricProperties.getCertificatePath()), StandardCharsets.UTF_8);

        X509Certificate certificate = Identities.readX509Certificate(certificateReader);

        BufferedReader privateKeyReader = Files.newBufferedReader(Paths.get(hyperLedgerFabricProperties.getPrivateKeyPath()), StandardCharsets.UTF_8);

        PrivateKey privateKey = Identities.readPrivateKey(privateKeyReader);

        Wallet wallet = Wallets.newInMemoryWallet();
        wallet.put("user1", Identities.newX509Identity(hyperLedgerFabricProperties.getMspId(), certificate, privateKey));

        Gateway.Builder builder = Gateway.createBuilder()
                .identity(wallet, "user1")
                .networkConfig(Paths.get("src/main/resources/networkConnection.json"));

        Gateway gateway = builder.connect();

        log.info("=========================================== connected fabric gateway {} ", gateway);
        return gateway;
    }

    @Bean
    public Network network(Gateway gateway) {
        return gateway.getNetwork(hyperLedgerFabricProperties.getChannel());
    }

    @Bean
    public Contract getContract(Network network) {
        return network.getContract("maotai-contract");
    }
}