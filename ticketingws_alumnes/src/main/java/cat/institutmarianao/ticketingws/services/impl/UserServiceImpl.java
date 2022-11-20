package cat.institutmarianao.ticketingws.services.impl;

import java.util.List;
import java.util.Optional;

import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import cat.institutmarianao.ticketingws.model.User;
import cat.institutmarianao.ticketingws.model.User.Role;
import cat.institutmarianao.ticketingws.repositories.UserRepository;
import cat.institutmarianao.ticketingws.services.UserService;
import cat.institutmarianao.ticketingws.specifications.UserWithFullName;
import cat.institutmarianao.ticketingws.specifications.UserWithRole;

@Service
public class UserServiceImpl implements UserService {
	private static final int DEFAULT_PAGE = 0;
	private static final int DEFAULT_SIZE = Integer.MAX_VALUE;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageSource messageSource;

	@Override
	public Page<User> findAll(@PositiveOrZero Integer page, @Positive Integer size, List<Order> orders, Role role,
			String fullName) {
		Specification<User> spec = Specification.where(new UserWithRole(role)).and(new UserWithFullName(fullName));

		Sort sort = orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);

		if (page == null) {
			page = DEFAULT_PAGE;
		}
		if (size == null) {
			size = DEFAULT_SIZE;
		}
		
		return userRepository.findAll(spec, PageRequest.of(page, size, sort));
	}

	@Override
	public Optional<User> findByUsername(@NotEmpty String username) {
		return userRepository.findById(username);
	}

	@Override
	public User getUserByUsername(@NotEmpty String username) {
		Optional<User> user = userRepository.findById(username);
		if (user.isEmpty()) {
			String errorMerrage = messageSource.getMessage("error.UserService.user.not.found",
					new Object[] { username }, LocaleContextHolder.getLocale());
			throw new ValidationException(errorMerrage);
		}
		return user.get();
	}

	@Override
	public User save(@NotNull User user) {
		
		/* ToDo */
		
		return userRepository.save(user);
	}

	@Override
	public void deleteByUsername(@NotEmpty String username) {
		userRepository.deleteById(username);
	}

}
