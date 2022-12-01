package cat.institutmarianao.ticketingws.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.util.Pair;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.institutmarianao.ticketingws.controllers.utils.SortParamsUtils;
import cat.institutmarianao.ticketingws.model.Room;
import cat.institutmarianao.ticketingws.model.User;
import cat.institutmarianao.ticketingws.model.User.Role;
import cat.institutmarianao.ticketingws.services.UserService;
import cat.institutmarianao.ticketingws.validation.groups.OnUserCreate;
import cat.institutmarianao.ticketingws.validation.groups.OnUserUpdate;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/find/all")
	public List<User> findAll(@RequestParam(value = "page", required = false) @PositiveOrZero Integer page,
			@RequestParam(value = "size", required = false) @Positive Integer size,
			@RequestParam(value = "sort", required = false, defaultValue = "username,asc") String[] sortFields,
			@RequestParam(value = "role", required = false) Role role,
			@RequestParam(value = "fullName", required = false) String fullName) {

		List<Order> orders = SortParamsUtils.getOrders(sortFields);
		
		return userService.findAll(page, size, orders, role, fullName).toList();
	}

	@GetMapping("/find/by/username/{username}")
	public Optional<User> findByUsername(@PathVariable("username") @NotBlank String username) {
		Optional<User> opUser = userService.findByUsername(username);
		if (opUser.isEmpty()) {
			return Optional.ofNullable(null);
		}
		
		return Optional.of(opUser.get());
	}
	
	@PostMapping("/save")
	@Validated(OnUserCreate.class)
	public User save(@RequestBody User user) {
		return saveOrUpdate(user);
	}

	@PutMapping("/update")
	@Validated(OnUserUpdate.class)
	public User update(@RequestBody User user) {
		return saveOrUpdate(user);
	}

	@DeleteMapping("/delete/by/username/{username}")
	public void deleteByUsername(@PathVariable("username") @NotBlank String username) {
		userService.deleteByUsername(username);
	}

	private User saveOrUpdate(User user) {
		if (user == null) {
			return null;
		}
		
		return userService.save(user);
	}

	@PostMapping("/login")
	public User login(@RequestBody @NotBlank Pair<String, String> login) {
		/* ToDo */
		return null;
	}
	
	@GetMapping(value = "/rooms/find/all")
	public List<Room> findRoomsAll() {
		/* ToDo */
		return null;
	}
	
	@GetMapping(value = "/companies/find/all")
	public List<String> findCompaniesAll() {
		/* ToDo */
		return null;
	}
}
