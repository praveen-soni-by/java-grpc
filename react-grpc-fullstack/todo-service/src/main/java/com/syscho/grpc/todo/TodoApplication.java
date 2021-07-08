package com.syscho.grpc.todo;

import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.grpc.netty.shaded.io.netty.internal.tcnative.SSLContext;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.net.ssl.SSLException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class TodoApplication {
    public static void main(String[] args) {

        SpringApplication.run(TodoApplication.class, args);
    }

    @Bean
    public GrpcServerConfigurer keepAliveServerConfigurer() {

        return serverBuilder -> {
            if (serverBuilder instanceof NettyServerBuilder) {

                    ((NettyServerBuilder) serverBuilder)
                            .keepAliveTime(30, TimeUnit.SECONDS)
                            .keepAliveTimeout(5, TimeUnit.SECONDS)
                            .permitKeepAliveWithoutCalls(true);

            }
        };
    }
}
