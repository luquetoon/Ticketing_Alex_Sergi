package cat.institutmarianao.ticketingws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Formula;

/**
 * <p>
 * Stores an issue reported by a user, who we will call <b>requester</b>
 * </p>
 * <p>
 * The ticket can have different categories (see {@link Category}) depending on
 * the kind of issue reported
 * </p>
 * <p>
 * The status of the ticked is stored in the tracking stack. Depending on the
 * actions that are stored there, the status can be:
 * <ul>
 * <li><b>pending </b> : when the tracking stack contains only a opening
 * action</li>
 * <li><b>assigned </b> : when the tracking stack contains an assignment and an
 * opening actions</li>
 * <li><b>in process </b> : when the tracking stack contains interventions
 * actions as well as an assignment and an opening</li>
 * <li><b>closed </b> : when top action of the stack is a close one</li>
 * </ul>
 * </p>
 *
 * @see Category
 * @see Opening
 * @see Assignment
 * @see Intervention
 * @see Close
 */

@Entity
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_DESCRIPTION = 500;

	/**
	 * <p>
	 * It contains the categories that group most common issues of a ticket
	 * </p>
	 */
	public enum Category {
		HARDWARE, SOFTWARE, PRINTER, NETWORK, SUPPORT, OTHERS
	}

	public enum Status {
		PENDING, IN_PROCESS, HISTORICAL
	}

	private Long id;

	private Category category;

	private String description;

	private List<Action> tracking;

	/* JPA */
	@Enumerated(EnumType.STRING) // Stored as string
	/* Hibernate */
	@Formula("(SELECT CASE a.type WHEN 'OPENING' THEN 'PENDING' "
			+ " WHEN 'ASSIGNMENT' THEN 'IN_PROCESS' WHEN 'INTERVENTION' THEN 'IN_PROCESS' "
			+ " WHEN 'CLOSE' THEN 'HISTORICAL' ELSE NULL END FROM actions a "
			+ " WHERE a.date=( SELECT MAX(last_action.date) FROM actions last_action "
			+ " WHERE last_action.ticket_id=a.ticket_id AND last_action.ticket_id=id ))")
	private Status status;

	public Ticket() {
		// POJO Contructor
		this.tracking = new LinkedList<Action>();
	}

	public Ticket(Category category, String description) {
		this.category = category;
		this.description = description;
		this.tracking = new LinkedList<Action>();
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description.trim();
	}

	public List<Action> getTracking() {
		return tracking;
	}

	public void setTracking(List<Action> tracking) {
		this.tracking = tracking;
	}

	public Status getStatus() {
		return status;
	}
	
	public String getPerformer() {
		LinkedList<Action> tracking = new LinkedList<Action>(this.getTracking());
		return tracking.getLast().getPerformer().getUsername();
	}
	
	public Date getOpeningDate() {
		LinkedList<Action> tracking = new LinkedList<Action>(this.getTracking());
		return tracking.getLast().getDate();
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Ticket)) {
			return false;
		}
		return Objects.equals(id, ((Ticket) obj).getId());
	}
}
