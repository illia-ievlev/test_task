package com.ievlev.test_task3;

import com.ievlev.test_task3.initializer.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.ByteArrayInputStream;

@SpringBootTest
class TestTask3ApplicationTests extends IntegrationTestBase {


    @Test
    public void loadContext(){
    provideInput("exit");
    }
    private void provideInput(String data) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data.getBytes());
        System.setIn(inputStream);
    }
}
