package study.KimyounghanSiljeonSpringDataJpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Item implements Persistable<Long> {
//public class Item{

  @Id
//  @GeneratedValue
  private Long id;

  @CreatedDate
  private LocalDateTime createdDate;


  public Item(Long id) {
    this.id = id;
  }


  @Override
  public Long getId() {
    return id;
  }


  @Override
  public boolean isNew() {
    return createdDate == null;
  }

}

