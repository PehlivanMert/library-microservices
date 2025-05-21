package org.pehlivan.mert.bookservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {
    "eureka.client.enabled=false",
    "spring.cloud.discovery.enabled=false"
})
class BookServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
