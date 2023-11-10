package com.example.doConnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.doConnect.entity.Token;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer>{
	Token findByToken(String token);
	
	@Query(value = "select * from Token inner join User on Token.user.id = User.id where User.id = ?1 and (Token.expired = false or Token.revoked = false)", nativeQuery = true)
	List<Token> findAllValidTokenByUser(int id);
}
