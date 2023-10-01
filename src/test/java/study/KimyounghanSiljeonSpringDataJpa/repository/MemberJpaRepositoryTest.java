package study.KimyounghanSiljeonSpringDataJpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.KimyounghanSiljeonSpringDataJpa.entity.Member;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
//@Rollback(false)
class MemberJpaRepositoryTest {

  @Autowired
  MemberJpaRepository memberJpaRepository;

  @PersistenceContext
  private EntityManager em;


  @Test
  void testMember() {
    Member member = new Member("memberA");
    Member saveMember = memberJpaRepository.save(member);

    Member findMember = memberJpaRepository.findById(saveMember.getId()).get();

    assertThat(findMember.getId()).isEqualTo(member.getId());
    assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
    assertThat(findMember).isEqualTo(member);
  }

  @Test
  void testCRUD() {
    Member member1 = new Member("member1");
    Member member2 = new Member("member2");
    memberJpaRepository.save(member1);
    memberJpaRepository.save(member2);

    Member findMember1 = memberJpaRepository.findById(member1.getId()).get();
    Member findMember2 = memberJpaRepository.findById(member2.getId()).get();
    assertThat(findMember1.getId()).isEqualTo(member1.getId());
    assertThat(findMember2.getId()).isEqualTo(member2.getId());

    List<Member> members = memberJpaRepository.findAll();
    assertThat(members.size()).isEqualTo(2);

    long count = memberJpaRepository.count();
    assertThat(count).isEqualTo(2);

    memberJpaRepository.delete(member1);
    memberJpaRepository.delete(member2);
    long deletedCount = memberJpaRepository.count();
    assertThat(deletedCount).isEqualTo(0);
  }

  @Test
  void findByUsernameAndAgeGreaterThan() {
    Member member1 = new Member("AAA", 10);
    Member member2 = new Member("AAA", 20);
    memberJpaRepository.save(member1);
    memberJpaRepository.save(member2);

    List<Member> members = memberJpaRepository.findByUsernameAndAgeGreaterThan("AAA", 15);
    assertThat(members.size()).isEqualTo(1);
    assertThat(members.get(0).getUsername()).isEqualTo("AAA");
    assertThat(members.get(0).getAge()).isEqualTo(20);
  }

  @Test
  void testNamedQuery_byDsLee(){
    // 네임드 쿼리 테스트. 유저네임으로 1명을 저장하고 해당 쿼리를 실행해보고, 적절한 유저를 불렀는지 확인.

    //g userDsLee 저장되어있음.
    //w userDeLee를 찾는 네임드 쿼리 실행
    //t 그 결과의 이름이 userDsLee임.

    //given
    Member dslee = new Member("userDsLee");
    memberJpaRepository.save(dslee);
    em.flush();
    em.clear();

    //when
    List<Member> result = memberJpaRepository.findByUsername("userDsLee");

    //then
    assertThat(result.size()).isEqualTo(1);
    assertThat(result.get(0).getUsername()).isEqualTo("userDsLee");
  }


  @Test
  void paging() {
    memberJpaRepository.save(new Member("member1", 10));
    memberJpaRepository.save(new Member("member2", 10));
    memberJpaRepository.save(new Member("member3", 10));
    memberJpaRepository.save(new Member("member4", 10));
    memberJpaRepository.save(new Member("member5", 10));

    int age = 10;
    int offset = 0;
    int limit = 3;

    List<Member> members = memberJpaRepository.findByPage(age, offset, limit);
    long totalCount = memberJpaRepository.totalCount(age);
    assertThat(members.size()).isEqualTo(limit);
    assertThat(members.get(0).getUsername()).isEqualTo("member5");
    assertThat(totalCount).isEqualTo(5);
  }

}
