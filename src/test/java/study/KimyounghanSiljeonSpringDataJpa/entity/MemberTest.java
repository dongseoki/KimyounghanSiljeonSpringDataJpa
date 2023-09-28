package study.KimyounghanSiljeonSpringDataJpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
//@Rollback(value = false)
class MemberTest {

  @PersistenceContext
  private EntityManager em;


  @Test
  void testEntity() {
    Team teamA = new Team("teamA");
    Team teamB = new Team("teamB");
    em.persist(teamA);
    em.persist(teamB);

    Member member1 = new Member("member1", 10, teamA);
    Member member2 = new Member("member2", 20, teamA);
    Member member3 = new Member("member3", 30, teamB);
    Member member4 = new Member("member4", 40, teamB);
    em.persist(member1);
    em.persist(member2);
    em.persist(member3);
    em.persist(member4);

    em.flush();
    em.clear();

    List<Member> members = em.createQuery("select m from Member m", Member.class)
                             .getResultList();
    members.forEach(m -> {
      System.out.println("member: " + m);
      System.out.println("-> member.team: " + m.getTeam());
    });
  }


  @Test
  void testEntit23234423y() {

//    int[][] answer = merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}});
    int[][] answer = merge(new int[][]{{1, 4}, {2, 3}});
    for (int[] ints : answer) {
      System.out.println(Arrays.toString(ints));
    }
  }

  public int[][] merge(int[][] intervals) {
    Arrays.sort(intervals, new Comparator<int[]>() {
      @Override
      public int compare(int[] o1, int[] o2) {
        return o1[0] - o2[0];
      }
    });
    int nOStartNum = intervals[0][0];
    int nOEndNum = intervals[0][1];
    int idx = 1;
    List<int[]> answer = new ArrayList();
    while(idx < intervals.length) {
      int startNum = intervals[idx][0];
      int endNum = intervals[idx][1];
      if (nOEndNum >= startNum) {
        nOEndNum = Math.max(nOEndNum, endNum);
      }else {
        answer.add(new int[]{nOStartNum, nOEndNum});
        // 겹치지 않아 새로운 묶음 확인.
        nOStartNum = startNum;
        nOEndNum = endNum;
      }
      idx++;
    }
    answer.add(new int[]{nOStartNum, nOEndNum});
    return answer.toArray(new int[][]{});


  }
}