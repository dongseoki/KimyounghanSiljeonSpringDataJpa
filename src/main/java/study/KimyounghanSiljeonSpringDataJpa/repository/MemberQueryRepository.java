package study.KimyounghanSiljeonSpringDataJpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import study.KimyounghanSiljeonSpringDataJpa.entity.Member;

import java.util.List;

@Repository
public class MemberQueryRepository {
  @PersistenceContext
  private EntityManager em;

  public List<Member> findMemberCustomQuery() {
    return em.createQuery("select m from Member m")
             .getResultList();
  }
}
