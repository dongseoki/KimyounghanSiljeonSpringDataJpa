package study.KimyounghanSiljeonSpringDataJpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.KimyounghanSiljeonSpringDataJpa.entity.Item;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemRepositoryTest {

  @Autowired
  private ItemRepository itemRepository;


  @Test
  void save() {
    Item item = new Item(2L);
//    Item item = new Item();
    itemRepository.save(item);
  }

}