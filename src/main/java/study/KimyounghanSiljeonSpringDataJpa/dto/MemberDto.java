package study.KimyounghanSiljeonSpringDataJpa.dto;

import lombok.Data;
import study.KimyounghanSiljeonSpringDataJpa.entity.Member;

@Data
public class MemberDto {

  private Long id;
  private String username;
  private String teamName;


  public MemberDto(Long id, String username, String teamName) {
    this.id = id;
    this.username = username;
    this.teamName = teamName;
  }

  public MemberDto(Member member) {
    this(member.getId(), member.getUsername(), null);
  }

}
