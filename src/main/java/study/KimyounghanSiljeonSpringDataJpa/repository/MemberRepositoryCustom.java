package study.KimyounghanSiljeonSpringDataJpa.repository;

import study.KimyounghanSiljeonSpringDataJpa.entity.Member;

import java.util.List;

public interface MemberRepositoryCustom {

  List<Member> findMemberCustom();

}