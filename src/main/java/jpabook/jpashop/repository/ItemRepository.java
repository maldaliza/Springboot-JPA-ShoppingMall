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

    /**
     * 상품 저장
     * @param item
     */
    public void save(Item item) {
        if (item.getId() == null) {     // Item은 처음에는 id가 없다.
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    /**
     * 상품 단건 조회
     * @param id
     * @return
     */
    public Item findOne(Long id) {
        Item result = em.find(Item.class, id);
        return result;
    }

    /**
     * 상품 다건 조회
     * @return
     */
    public List<Item> findAll() {
        List<Item> result = em.createQuery("select i from Item i", Item.class).getResultList();
        return result;
    }
}
