package study.KimyounghanSiljeonSpringDataJpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.KimyounghanSiljeonSpringDataJpa.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
