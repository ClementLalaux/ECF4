package com.example.utilisateurservice;

import com.example.utilisateurservice.entity.User;
import com.example.utilisateurservice.repository.UserRepository;
import com.example.utilisateurservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class UtilisateurServiceApplicationTests {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	@Test
	public void shouldAddNewUserWhenValidData(){
		User user = new User("Clement","lalauxclement@gmail.com");
		Mockito.when(userRepository.save(user)).thenReturn(user);

		User user1 = userService.createUser("Clement","lalauxclement@gmail.com");
		Assertions.assertEquals(user,user1);
	}

	@Test
	public void shouldReturnExceptionWhenUserByIdNotFound(){
		User user = new User(1L,"Clement","lalauxclement@gmail.com");
		Mockito.when(userRepository.save(user)).thenReturn(user);
		Long id = 100L;
		Assertions.assertThrowsExactly(RuntimeException.class,()->{
			userService.getUserById(id);
		});
	}

	@Test
	public void shouldReturnUserByIdWhenFound(){
		User user = new User(1L,"Clement","lalauxclement@gmail.com");
		Optional<User> user1 = Optional.of(user);
		Mockito.when(userRepository.findById(1L)).thenReturn(user1);
		Assertions.assertEquals(user,userService.getUserById(1L));

	}

}
