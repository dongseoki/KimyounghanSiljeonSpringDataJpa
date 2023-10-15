package study.KimyounghanSiljeonSpringDataJpa.controller;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import study.KimyounghanSiljeonSpringDataJpa.dto.MemberDto;
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


  @GetMapping("/members1_1")
  public Page<Member> list1_1(Pageable pageable) {
    return memberRepository.findAll(pageable);
  }

  @GetMapping("/members1_2")
  public Page<MemberDto> list1_2(Pageable pageable) {
    return memberRepository.findAll(pageable).map(MemberDto::new);
  }


  @GetMapping("/members_page")
  public Page<Member> list2(@PageableDefault(size = 5) Pageable pageable) {
    return memberRepository.findAll(pageable);
  }


  @PostConstruct
  void init() {
    for (int i = 0; i < 100; ++i) {
      memberRepository.save(new Member("user" + i, i));
    }
  }

}
