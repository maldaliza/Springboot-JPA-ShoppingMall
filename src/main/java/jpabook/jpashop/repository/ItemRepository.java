package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {     // Item은 처음에는 id가 없다.
            em.persist(item);
        } else {        // Item이 id가 있는 경우는 먼저 DB에 등록이 되있는 경우다.
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        Item result = em.find(Item.class, id);
        return result;
    }

    public List<Item> findAll() {
        List<Item> result = em.createQuery("select i from Item i", Item.class)
                .getResultList();
        return result;
    }
}
