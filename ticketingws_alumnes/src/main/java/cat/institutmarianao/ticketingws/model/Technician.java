package cat.institutmarianao.ticketingws.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;

/**
 * <p>
 * A technical user, the one that can perform intervention actions. Can also
 * perform close actions only if he has any intervention in the referenced
 * ticket
 * </p>
 *
 * <p>
 * A technician has role <code>User.Role.TECHNICIAN</code> and his authorities
 * list contains <code>{"TECHNICIAN"}</code>
 * </p>
 *
 * @see User
 * @see Role
 * @see Intervention
 * @see Close
 */

@Entity
public class Technician extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_COMPANY = 100;

	private String company;

	public Technician() {
		// POJO constructor
	}
	
	public Technician(String username, String password, String fullName, Integer extension, String company) {
		super(username, password, fullName, extension);
		this.company = company;
	}
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public boolean isTechnician() {
		return true;
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
		if (!(obj instanceof Technician)) {
			return false;
		}
		return Objects.equals(username, ((Technician) obj).getUsername());
	}
}
