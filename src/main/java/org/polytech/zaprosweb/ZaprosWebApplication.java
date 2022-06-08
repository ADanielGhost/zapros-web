package org.polytech.zaprosweb;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class ZaprosWebApplication {

	public static void main(String[] args) {
        new SpringApplicationBuilder()
            .profiles("dev")
            .sources(ZaprosWebApplication.class)
            .run(args);
    }
}
