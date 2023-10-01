package study.KimyounghanSiljeonSpringDataJpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import study.KimyounghanSiljeonSpringDataJpa.dto.MemberDto;
import study.KimyounghanSiljeonSpringDataJpa.entity.Member;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

  public List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

  @Query(name = "Member.findByUsername")
  List<Member> findByUsername(@Param("username") String username);

  @Query("select m from Member m where m.username = :username and m.age = :age")
  List<Member> findMember(@Param("username") String username, @Param("age") int age);

  @Query("select m.username from Member m")
  List<String> findUsernameList();


  @Query("select new study.KimyounghanSiljeonSpringDataJpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
  List<MemberDto> findMemberDto();

  @Query("select m from Member m where m.username in :names")
  List<Member> findByNames(@Param("names") Collection<String> usernames);

  List<Member> findMembersByUsername(String name);

  Member findMemberByUsername(String name);

  Optional<Member> findOptionalMemberByUsername(String name);


  Page<Member> findByAge(int age, Pageable pageable);

  Slice<Member> findSliceByAge(int age, Pageable pageable);

  List<Member> findListByAge(int age, Pageable pageable);

  @Query(value = "select m from Member m",
      countQuery = "select count(m.username) from Member m")
  Page<Member> findMemberAllCountBy(Pageable pageable);

  @Modifying(clearAutomatically = true)
  @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
  int bulkAgePlus(@Param("age") int age);

}