package app.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByLogin(String login);
	
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.password = :password WHERE u.login = :login")
	public void changePasswordByLogin(@Param(value = "password") String password, @Param(value = "login") String login);
}
