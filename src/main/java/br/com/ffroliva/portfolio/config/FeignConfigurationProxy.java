package br.com.ffroliva.portfolio.config;

import feign.Feign;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.net.Proxy;

@Configuration
public class FeignConfigurationProxy {

    @Value("${proxy.url}")
    private String HOST_PROXY;
    @Value("${proxy.port}")
    private Integer PORT_PROXY;

    private OkHttpClient okHttpClient;
    private Proxy proxy;

    @PostConstruct
    public void init() {
        buildProxy();
        buildOkHttpClient();
    }

    @Bean(name = "feign")
    public Feign buildClient() {
        return Feign.builder().client(new feign.okhttp.OkHttpClient(okHttpClient)).build();
    }


    @Bean(name = "okhttpclient")
    public OkHttpClient okHttpClient() {
        return okHttpClient;
    }

    public void buildOkHttpClient() {
        buildProxy();
        okHttpClient = new OkHttpClient.Builder().proxy(proxy).build();
    }

    public void buildProxy() {
        proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(HOST_PROXY, PORT_PROXY));
    }

}
