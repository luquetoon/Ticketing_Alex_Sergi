package cat.institutmarianao.ticketingws.services;

import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort.Order;

import cat.institutmarianao.ticketingws.model.User;
import cat.institutmarianao.ticketingws.model.User.Role;

public interface UserService {

	Page<User> findAll(@PositiveOrZero Integer page, @Positive Integer size, List<Order> orders, Role role,
			String fullName);

	Optional<User> findByUsername(@NotEmpty String username);

	User getUserByUsername(@NotEmpty String username);
	
	User save(@NotNull User user);

	void deleteByUsername(@NotEmpty String username);

}