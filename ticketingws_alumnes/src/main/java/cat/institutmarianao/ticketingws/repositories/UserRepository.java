package cat.institutmarianao.ticketingws.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cat.institutmarianao.ticketingws.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

	@Query("SELECT DISTINCT(t.company) FROM Technician t")
	public List<String> findDistinctCompanies();

}
