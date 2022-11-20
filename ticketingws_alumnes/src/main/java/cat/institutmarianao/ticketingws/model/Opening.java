package cat.institutmarianao.ticketingws.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Opening of a ticket by an employee in a certain date
 *
 */
public class Opening extends Action implements Serializable {

	private static final long serialVersionUID = 1L;

	public Opening() {
		// POJO constructor
		this.type = Action.Type.OPENING;
	}

	public Opening(User performer, Ticket ticket) {
		super(performer, ticket);
		this.type = Action.Type.OPENING;
	}

	@Override
	public boolean isOpening() {
		return true;
	}
	
	@Override
	public String getSummary() {
		return "";
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Opening)) {
			return false;
		}
		return Objects.equals(id, ((Opening) obj).getId());
	}
}
