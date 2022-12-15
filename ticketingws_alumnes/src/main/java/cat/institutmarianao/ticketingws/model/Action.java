package cat.institutmarianao.ticketingws.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * <p>
 * Represents any performed action by a user on a ticket in a certain date. They
 * are stored in the tracking stack of the ticket.
 * </p>
 *
 * <p>
 * Actions can be:
 * <ul>
 * <li><b>opening</b>: when an employee opens a new ticket</li>
 * <li><b>assignment</b>: when a supervisor assigns a ticket to a
 * technician</li>
 * <li><b>intervention</b>: when a technician performs an intervention</li>
 * <li><b>close</b>: when a the ticket is closed, for instance, when considered
 * that is resolved. It can be done by:
 * <ul>
 * <li>the <b>employee</b> that has opened the ticket</li>
 * <li>the <b>technician</b> who has interventions on the ticket</li>
 * <li>a <b>supervisor</b></li>
 * </ul>
 * </li>
 * </ul>
 * </p>
 *
 * @see Opening
 * @see Assignment
 * @see Intervention
 * @see Close
 * @see User
 * @see Employee
 * @see Technician
 * @see Supervisor
 */
@Entity
public abstract class Action implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Values for type - MUST be constants */
	public static final String OPENING = "OPENING";
	public static final String ASSIGNMENT = "ASSIGNMENT";
	public static final String INTERVENTION = "INTERVENTION";
	public static final String CLOSE = "CLOSE";

	public enum Type {
		OPENING, ASSIGNMENT, INTERVENTION, CLOSE
	}
	@Id
	protected Long id;

	protected Type type;

	protected User performer;

	protected Date date;

	protected Ticket ticket;

	protected Action() {
		// POJO Contructor
	}

	public Action(User performer, Ticket ticket) {
		this.performer = performer;
		this.ticket = ticket;
		this.date = new Date();	// Now()
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public User getPerformer() {
		return performer;
	}

	public void setPerformer(User performer) {
		this.performer = performer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public boolean isOpening() {
		return false;
	}
	
	public boolean isAssignment() {
		return false;
	}
	
	public boolean isIntervention() {
		return false;
	}
	
	public boolean isClose() {
		return false;
	}

	public abstract String getSummary();
	
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Action)) {
			return false;
		}
		return Objects.equals(id, ((Action) obj).getId());
	}
}
