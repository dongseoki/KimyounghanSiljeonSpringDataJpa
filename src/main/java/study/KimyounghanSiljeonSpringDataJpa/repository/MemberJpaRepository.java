package study.KimyounghanSiljeonSpringDataJpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import study.KimyounghanSiljeonSpringDataJpa.entity.Member;

import java.util.List;
import java.util.Optional;


@Repository
public class MemberJpaRepository {

  @PersistenceContext
  private EntityManager em;


  public Member save(Member member) {
    em.persist(member);
    return member;
  }


  public void delete(Member member) {
    em.remove(member);
  }


  public List<Member> findAll() {
    return em.createQuery("select m from Member m", Member.class)
             .getResultList();
  }


  public long count() {
    return em.createQuery("select count(m) from Member m", Long.class)
             .getSingleResult();
  }


  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(em.find(Member.class, id));
  }


  public Member find(Long id) {
    return em.find(Member.class, id);
  }

  public List<Member> findByUsernameAndAgeGreaterThan(String username, int age) {
    String jpql = "select m from Member m where m.username = :username and m.age > :age";
    return em.createQuery(jpql, Member.class)
             .setParameter("username", username)
             .setParameter("age", age)
             .getResultList();
  }

}

