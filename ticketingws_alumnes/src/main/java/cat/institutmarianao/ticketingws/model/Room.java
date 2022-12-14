package cat.institutmarianao.ticketingws.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;

/**
 * A name, there the employee is
 */

@Entity
public class Room implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_ROOM = 100;

	private Long id;

	private String name;

	public Room() {
		// POJO constructor
	}

	public Room(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		if (!(obj instanceof Room)) {
			return false;
		}
		return Objects.equals(id, ((Room) obj).getId());
	}
}
