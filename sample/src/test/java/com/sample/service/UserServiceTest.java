package com.sample.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.sample.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService service;

    @Test
    public void testSave() {
        User user1 = new User();
        user1.setName("Example User1");
        user1.setEmail("User1@Example.Com");
        service.save(user1);

        assertThat(service.findById(1).getName(), is("Example User1"));
        assertThat(service.findById(1).getEmail(), is("user1@example.com"));
    }

}
