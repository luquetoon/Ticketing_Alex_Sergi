package cat.institutmarianao.ticketingws.controllers;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import cat.institutmarianao.ticketingws.controllers.utils.SortParamsUtils;
import cat.institutmarianao.ticketingws.mocks.data.MockUsers;
import cat.institutmarianao.ticketingws.model.User;
import cat.institutmarianao.ticketingws.services.UserService;

@TestInstance(TestInstance.Lifecycle.PER_CLASS) // <-Allow BeforeAll not to be static
@AutoConfigureWebMvc
@WebMvcTest(UserController.class)
public class UserControllerTest {

	private static final String USERS_PATH = "/users";
	private static final String USERS_FIND_ALL_PATH = USERS_PATH + "/find/all";
	private static final String USERS_FIND_USER_BY_USERNAME = USERS_PATH + "/find/by/username/{username}";
	private static final String USERS_SAVE_PATH = USERS_PATH + "/save";
	/*private static final String USERS_UPDATE_PATH = USERS_PATH + "/update";
	private static final String USERS_DELETE_BY_USERNAME = USERS_PATH + "/delete/by/username/{username}";
	private static final String USERS_LOGIN_PATH = USERS_PATH + "/login";
	private static final String ROOMS_FIND_ALL_PATH = "/rooms/find/all";
	private static final String COMPANIES_FIND_ALL_PATH = "companies/find/all";*/

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Nested
	@DisplayName("GET " + USERS_FIND_ALL_PATH)
	class FindAllUsers {

		@Nested
		@DisplayName("Without params")
		class WithoutParams {
			@Test
			@DisplayName("When there is a list of users to return...")
			void whenHasResultsShouldReturnAllUsersSortedByUsernameDesc() throws Exception {

				List<User> expectedUsers = new ArrayList<>(MockUsers.USERS);
				Collections.sort(expectedUsers, (u1, u2) -> u1.getUsername().compareTo(u2.getUsername()));

				// Mock service
				when(userService.findAll(null, null, SortParamsUtils.getOrders(new String[] { "username,asc" }), null,
						null)).thenReturn(page(expectedUsers));

				// Call controller and asserts
				ResultActions result = mockMvc.perform(get(USERS_FIND_ALL_PATH).accept(MediaType.APPLICATION_JSON))
						.andDo(print()).andExpect(status().isOk())
						.andExpect(jsonPath("$.content", hasSize(expectedUsers.size())));
				checkJsonResults(result, expectedUsers);
			}

			@Test
			@DisplayName("When there is no users to return...")
			void whenHasNoResultsShouldReturnEmptyContent() throws Exception {
				// Mock service
				when(userService.findAll(null, null, SortParamsUtils.getOrders(new String[] { "username,asc" }), null,
						null)).thenReturn(page(new ArrayList<User>()));

				// Call controller and asserts
				mockMvc.perform(get(USERS_FIND_ALL_PATH).accept(MediaType.APPLICATION_JSON)).andDo(print())
						.andExpect(status().is2xxSuccessful()).andExpect(jsonPath("$.content", hasSize(0)));
			}
		}

		// TODO - With filter params
		// TODO - With sort params
		// TODO - With multiple sort params
	}

	@Nested
	@DisplayName("GET " + USERS_FIND_USER_BY_USERNAME)
	class FindUserByUsername {
		@Test
		@DisplayName("When there is a user with this username...")
		void whenHasResultShouldReturnTheUser() throws Exception {

			int random = new Random().nextInt(MockUsers.USERS.size());
			User expectedUser = MockUsers.USERS.get(random);
			Optional<User> returnedUser = Optional.of(expectedUser);

			// Mock service
			when(userService.findByUsername(expectedUser.getUsername())).thenReturn(returnedUser);

			// Call controller and asserts
			ResultActions result = mockMvc.perform(
					get(USERS_FIND_USER_BY_USERNAME, expectedUser.getUsername()).accept(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().isOk());

			checkJsonResult(result, expectedUser);
		}

		@Test
		@DisplayName("When there is no users with this username...")
		void whenHasNoResultShouldReturnNull() throws Exception {

			String username = RandomString.make(20);
			Optional<User> returnedUser = Optional.ofNullable(null);

			// Mock service
			when(userService.findByUsername(username)).thenReturn(returnedUser);

			// Call controller and asserts
			mockMvc.perform(get(USERS_FIND_USER_BY_USERNAME, username).accept(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().is2xxSuccessful()).andExpect(content().string("null"));
		}
	}

	@Nested
	@DisplayName("POST " + USERS_SAVE_PATH)
	class Save {

		// Use this mapper to serialize the password
		private ObjectMapper objectMapper = JsonMapper.builder().disable(MapperFeature.USE_ANNOTATIONS).build();

		@Test
		@DisplayName("When save a user...")
		void whenSaveShouldReturnTheUser() throws Exception {
			// Prepare data
			User expectedUser = MockUsers.generateRandomUser();

			// Mock service
			when(userService.save(expectedUser)).thenReturn(expectedUser);

			// Call controller and asserts
			ResultActions result = mockMvc
					.perform(post(USERS_SAVE_PATH).contentType(MediaType.APPLICATION_JSON)
							.content(objectMapper.writeValueAsString(expectedUser)).accept(MediaType.APPLICATION_JSON))
					.andDo(print()).andExpect(status().is2xxSuccessful());
			checkJsonResult(result, expectedUser);
		}

		@Test
		@DisplayName("When save a user without username")
		void whenSaveWithoutUsernameShouldReturn4xxError() throws Exception {
			// Prepare data
			User user = MockUsers.generateRandomUser();
			user.setUsername(null);

			// Mock service
			when(userService.save(user)).thenReturn(user);

			// Call controller and asserts
			mockMvc.perform(post(USERS_SAVE_PATH).contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(user)).accept(MediaType.APPLICATION_JSON)).andDo(print())
					.andExpect(status().is4xxClientError());
		}

		// TODO - Other possible errors
	}

	// TODO Update tests
	// TODO Delete tests
	// TODO Login tests
	// TODO Find rooms tests
	// TODO Find companies tests
	
	private void checkJsonResults(ResultActions result, List<User> sortedUsers) throws Exception {
		for (int i = 0; i < sortedUsers.size(); i++) {
			User user = sortedUsers.get(i);
			result = result.andExpect(jsonPath("$.content[" + i + "].username", equalTo(user.getUsername())));
			result = result.andExpect(jsonPath("$.content[" + i + "].role", equalTo(user.getRole().toString())));
			result = result.andExpect(jsonPath("$.content[" + i + "].password").doesNotExist());
			result = result.andExpect(jsonPath("$.content[" + i + "].fullName", equalTo(user.getFullName())));
			result = result.andExpect(jsonPath("$.content[" + i + "].extension", equalTo(user.getExtension())));
			result = result.andExpect(jsonPath("$.content[" + i + "].room").doesNotExist());
			result = result.andExpect(jsonPath("$.content[" + i + "].company").doesNotExist());
			result = result.andExpect(jsonPath("$.content[" + i + "].location", equalTo(user.getLocation())));
		}
	}

	private void checkJsonResult(ResultActions result, User user) throws Exception {
		result = result.andExpect(jsonPath("$.username", equalTo(user.getUsername())));
		result = result.andExpect(jsonPath("$.role", equalTo(user.getRole().toString())));
		result = result.andExpect(jsonPath("$.password").doesNotExist());
		result = result.andExpect(jsonPath("$.fullName", equalTo(user.getFullName())));
		result = result.andExpect(jsonPath("$.extension", equalTo(user.getExtension())));
		result = result.andExpect(jsonPath("$.room").doesNotExist());
		result = result.andExpect(jsonPath("$.company").doesNotExist());
		result = result.andExpect(jsonPath("$.location", equalTo(user.getLocation())));
	}

	private Page<User> page(List<User> content) {
		return new PageImpl<>(content);
	}
	
}