package cat.institutmarianao.ticketingws.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.institutmarianao.ticketingws.model.Action;
import cat.institutmarianao.ticketingws.model.Ticket;
import cat.institutmarianao.ticketingws.model.Ticket.Category;
import cat.institutmarianao.ticketingws.model.Ticket.Status;

@RestController
@RequestMapping("/tickets")
public class TicketController {
	/*
	@Autowired
	private UserService userService;
	@Autowired
	private TicketService ticketService;

	@Autowired
	private ActionService actionService;
	 */

	@GetMapping("/find/all")
	public List<Ticket> findAll(@RequestParam(value = "page", required = false) @PositiveOrZero Integer page,
			@RequestParam(value = "size", required = false) @Positive Integer size,
			@RequestParam(value = "sort", required = false, defaultValue = "date,asc") String[] sortFields,
			@RequestParam(value = "status", required = false) Status status,
			@RequestParam(value = "reportedBy", required = false) String reportedBy,
			@RequestParam(value = "category", required = false) Category category,
			@RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date from,
			@RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date to) {

		/* ToDo */
		return null;
	}

	@GetMapping("/find/all/PENDING")
	public List<Ticket> findAllPending(@RequestParam(value = "page", required = false) @PositiveOrZero Integer page,
			@RequestParam(value = "size", required = false) @Positive Integer size,
			@RequestParam(value = "sort", required = false, defaultValue = "date,asc") String[] sortFields,
			@RequestParam(value = "reportedBy", required = false) String reportedBy,
			@RequestParam(value = "category", required = false) Category category,
			@RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date from,
			@RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date to) {

		/* ToDo */
		return null;
	}

	@GetMapping("/find/all/IN_PROCESS")
	public List<Ticket> findAllInProcess(
			@RequestParam(value = "page", required = false) @PositiveOrZero Integer page,
			@RequestParam(value = "size", required = false) @Positive Integer size,
			@RequestParam(value = "sort", required = false, defaultValue = "date,asc") String[] sortFields,
			@RequestParam(value = "reportedBy", required = false) String reportedBy,
			@RequestParam(value = "category", required = false) Category category,
			@RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date from,
			@RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date to) {

		/* ToDo */
		return null;
	}

	@GetMapping("/find/all/HISTORICAL")
	public List<Ticket> findAllHistorical(
			@RequestParam(value = "page", required = false) @PositiveOrZero Integer page,
			@RequestParam(value = "size", required = false) @Positive Integer size,
			@RequestParam(value = "sort", required = false, defaultValue = "date,asc") String[] sortFields,
			@RequestParam(value = "reportedBy", required = false) String reportedBy,
			@RequestParam(value = "category", required = false) Category category,
			@RequestParam(value = "from", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date from,
			@RequestParam(value = "to", required = false) @DateTimeFormat(pattern = "dd/MM/yyyy") Date to) {

		/* ToDo */
		return null;
	}

	@GetMapping("/find/by/id/{ticketId}")
	public Optional<Ticket> findById(@PathVariable("ticketId") @Positive Long ticketId) {
		/* ToDo */
		return null;
	}

	@GetMapping("/find/tracking/by/id/{ticketId}")
	public Iterable<Action> findTrackingByTicketId(@PathVariable("ticketId") @Positive Long ticketId) {
		/* ToDo */
		return null;
	}

	@GetMapping("/open")
	public Ticket open(@RequestParam(value = "performer", required = true) String performerId, 
						@RequestParam(value = "category", required = true) Category category, 
						@RequestParam(value = "description", required = true) String description) {
		/* ToDo */
		return null;
	}
	
	@GetMapping("/assignment")
	public Ticket assignment(@RequestParam(value = "ticket", required = true) Long ticketId, 
							@RequestParam(value = "performer", required = true) String supervisorId, 
							@RequestParam(value = "technician", required = true) String technicianId, 
							@RequestParam(value = "priority", required = true) Integer priority) throws Exception {
		/* ToDo */
		return null;
	}
	
	@GetMapping("/intervention")
	public Ticket intervention(@RequestParam(value = "ticket", required = true) Long ticketId, 
								@RequestParam(value = "performer", required = true) String technicianId, 
								@RequestParam(value = "duration", required = true) Integer duration, 
								@RequestParam(value = "description", required = true) String description) {
		/* ToDo */
		return null;
	}
	
	@GetMapping("/close")
	public Ticket close(@RequestParam(value = "ticket", required = true) Long ticketId, 
						@RequestParam(value = "performer", required = true) String performerId) {
		/* ToDo */
		return null;
	}
}
