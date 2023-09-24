package study.KimyounghanSiljeonSpringDataJpa.repository;

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

}
