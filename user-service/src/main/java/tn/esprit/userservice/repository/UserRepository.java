package tn.esprit.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.userservice.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.login= :login")
    public User Auth(@Param("login") String login);
    @Query("SELECT u FROM User u WHERE u.role= 1")
    public List<User> WhereAdmin();
    @Query("select case when count(u) > 0 then true else false end from User u WHERE u.login= :login AND u.password = :password")
    public boolean existence(@Param("login") String login,@Param("password") String pwd);
    User findById(long id);
    @Query("SELECT u FROM User u WHERE u.login= :login AND u.id = :id")
    User findByLogin(@Param("login") String login,@Param("id") long id);
    @Query("SELECT u FROM User u WHERE u.email= :email AND u.id = :id")
    User findByEmail(@Param("email") String login,@Param("id") long id);
    @Query("SELECT u.id FROM User u WHERE u.login= :login")
    long SelectByid(@Param("login") String login);
    @Query("select u from User u join Verification_Code v on u.id=v.user_id WHERE u.login= :login")
    User isverified(String login);
    @Query("SELECT u FROM User u WHERE u.email= :email")
    User sms(@Param("email") String email);
    @Query("select case when count(u) > 0 then true else false end from User u WHERE u.email= :email OR u.login = :login")
    public boolean Existmail(@Param("email") String email,@Param("login") String login);
}