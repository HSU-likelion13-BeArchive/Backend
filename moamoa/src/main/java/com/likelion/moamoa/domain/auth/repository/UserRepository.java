package com.likelion.moamoa.domain.auth.repository;

import com.likelion.moamoa.domain.auth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// User 엔티티 DB 연동, 회원 가입 중복 검사
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLoginId(String loginId);

    // User라는 객체가 존재하면 값을 담고, 존재하지 않으면 비어있는 상태로 반환
    Optional<User> findByLoginId(String loginId);

    String userId(Long userId);
}
