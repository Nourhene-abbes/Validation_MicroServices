package tn.esprit.userservice.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tn.esprit.userservice.model.User;
import tn.esprit.userservice.model.Verification_Code;


public interface VerificationCodeRepository extends JpaRepository<Verification_Code, Long>  {
    @Query("SELECT u FROM Verification_Code u WHERE u.code= :code AND u.user_id= :user_id")
    Verification_Code CheckVerification(@Param("code") String code,@Param("user_id") long user_id);
    @Modifying
    @Transactional
    @Query("DELETE FROM Verification_Code u WHERE u.user_id= :user_id")
    void DeletedVerific(@Param("user_id") User user_id);
}
