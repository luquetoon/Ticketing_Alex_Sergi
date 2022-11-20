package cat.institutmarianao.ticketingws;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import cat.institutmarianao.ticketingws.repositories.UserRepository;
import cat.institutmarianao.ticketingws.services.UserService;

@SpringBootTest
class TicketingwsApplicationTests {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MessageSource messageSource;

	@Test
	void contextLoads() {
		assertThat(userService).isNotNull();
		assertThat(userRepository).isNotNull();
		assertThat(modelMapper).isNotNull();
		assertThat(messageSource).isNotNull();
	}

}
