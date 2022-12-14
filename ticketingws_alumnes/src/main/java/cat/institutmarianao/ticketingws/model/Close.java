package cat.institutmarianao.ticketingws.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;

/**
 * Close of a ticket by a performer in a certain date
 */

@Entity
public class Close extends Action implements Serializable {

	private static final long serialVersionUID = 1L;

	public Close() {
		// POJO constructor
		this.type = Action.Type.CLOSE;
	}

	public Close(User performer, Ticket ticket) {
		super(performer, ticket);
		this.type = Action.Type.CLOSE;
	}

	@Override
	public boolean isClose() {
		return true;
	}
	
	@Override
	public String getSummary() {
		return "Closed. Ticket solved";
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
		if (!(obj instanceof Close)) {
			return false;
		}
		return Objects.equals(id, ((Close) obj).getId());
	}
}
