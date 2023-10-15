package study.KimyounghanSiljeonSpringDataJpa.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.KimyounghanSiljeonSpringDataJpa.entity.Member;
import study.KimyounghanSiljeonSpringDataJpa.repository.MemberRepository;

@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberRepository memberRepository;


  @GetMapping("/members/{id}")
  public String findMember(@PathVariable("id") Long id) {
    return memberRepository.findById(id).map(Member::getUsername).orElse("");
  }


  @GetMapping("/members2/{id}")
  public String findMember2(@PathVariable("id") Member member) {
    return member.getUsername();
  }


  @PostConstruct
  void init() {
    memberRepository.save(new Member("userA"));
  }

}
