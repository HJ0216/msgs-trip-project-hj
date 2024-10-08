package com.msgs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan
@EntityScan("com.msgs.domain.*")
@EnableJpaRepositories({"com.msgs.domain.*", "com.msgs.domain.*"})
@EnableJpaAuditing
public class MsgsApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsgsApplication.class, args);
  }

}

