package study.KimyounghanSiljeonSpringDataJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import study.KimyounghanSiljeonSpringDataJpa.entity.Member;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  public List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

  @Query(name = "Member.findByUsername")
  List<Member> findByUsername(@Param("username") String username);

  @Query("select m from Member m where m.username = :username and m.age = :age")
  List<Member> findMember(@Param("username") String username, @Param("age") int age);

}