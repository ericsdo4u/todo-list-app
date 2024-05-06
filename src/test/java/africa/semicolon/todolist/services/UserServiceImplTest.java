package africa.semicolon.todolist.services;

import africa.semicolon.todolist.data.repository.UserRepository;
import africa.semicolon.todolist.dtos.SignUpRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository repository;

    @BeforeEach
    public void setUp(){
        repository.deleteAll();
    }
    @Test
    public void testThatUserCanRegister(){
        SignUpRequest request = new SignUpRequest();
        request.setUsername("donald");
        request.setPassword("1234");
        userService.signUp(request);
        assertEquals(1,userService.getNumberOfRegisterUsers());
    }
}