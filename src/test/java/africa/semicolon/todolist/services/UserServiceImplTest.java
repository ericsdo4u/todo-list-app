package africa.semicolon.todolist.services;

import africa.semicolon.todolist.data.model.User;
import africa.semicolon.todolist.data.repository.UserRepository;
import africa.semicolon.todolist.dtos.LoginRequest;
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
    private User user;

    @BeforeEach
    public void setUp(){
        user = new User();
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
    @Test
    public void testThatTwoUsersCanBeRegister(){
        SignUpRequest request = new SignUpRequest();
        request.setUsername("donald");
        request.setPassword("1234");
        userService.signUp(request);

        SignUpRequest request1 = new SignUpRequest();
        request1.setUsername("eric");
        request1.setPassword("1234");
        userService.signUp(request1);
        assertEquals(2, userService.getNumberOfRegisterUsers());
    }
    @Test
    public void testThatUserCanLogin(){
        SignUpRequest request = new SignUpRequest();
        request.setUsername("donald");
        request.setPassword("1234");
        userService.signUp(request);

        LoginRequest request1 = new LoginRequest();
        request1.setUsername("donald");
        request1.setPassword("1234");
        userService.login(request1);
        assertFalse(user.isLock());
    }
}