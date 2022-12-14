package cat.institutmarianao.ticketingws.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;


/**
 * Assignació del ticket per part del supervisor a un tècnic indicant la
 * priority corresponent en una determinada date
 *
 * Durant la creació de l'assignació: - La priority ha de tenir un valor entre 1
 * i 9, si el valor indicat està fora d'aquest rang indicar 1 - Podem suposar
 * que el tècnic no serà nul
 *
 *
 * No es pot actualitzar cap atribut a un valor incorrecte
 *
 */

@Entity

public class Assignment extends Action implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final int MIN_PRIORITAT = 1;
	public static final int MAX_PRIORITAT = 9;

	private Technician technician;

	private Integer priority;

	public Assignment() {
		// POJO constructor
		this.type = Action.Type.ASSIGNMENT;
	}
	
	public Assignment(User performer, Ticket ticket, Technician technician, Integer priority) {
		super(performer, ticket);
		this.technician = technician;
		this.priority = priority;
		this.type = Action.Type.ASSIGNMENT;
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	@Override
	public boolean isAssignment() {
		return true;
	}

	@Override
	public String getSummary() {
		return "Ticket assigned to "+technician.getFullName()+" ("+technician.getCompany()+")";
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
		if (!(obj instanceof Assignment)) {
			return false;
		}
		return Objects.equals(id, ((Assignment) obj).getId());
	}

}
