package study.KimyounghanSiljeonSpringDataJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.KimyounghanSiljeonSpringDataJpa.entity.Member;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  public List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
}