package cat.institutmarianao.ticketingws.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * <p>
 * An employee that opens tickets. He can also close his own ticket.
 * </p>
 * <p>
 * An employee has role <code>User.Role.EMPLOYEE</code> and his authorities list
 * contains <code>{"EMPLOYEE"}</code>
 * </p>
 *
 * @see User
 * @see Role
 * @see Opening
 * @see Close
 *
 */
public class Employee extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_PLACE = 100;

	private Room room;

	private String place;

	public Employee() {
		// POJO constructor
	}

	public Employee(String username, String password, String fullName, Integer extension, Room room, String place) {
		super(username, password, fullName, extension);
		this.room = room;
		this.place = place;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String lloc) {
		place = lloc;
	}

	public boolean isEmployee() {
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
		if (!(obj instanceof Employee)) {
			return false;
		}
		return Objects.equals(username, ((Employee) obj).getUsername());
	}
}
