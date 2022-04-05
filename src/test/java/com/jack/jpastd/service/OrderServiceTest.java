package com.jack.jpastd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;

import com.jack.jpastd.domain.Address;
import com.jack.jpastd.domain.Member;
import com.jack.jpastd.domain.Order;
import com.jack.jpastd.domain.OrderStatus;
import com.jack.jpastd.domain.item.Book;
import com.jack.jpastd.repository.OrderRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class OrderServiceTest {

    @Autowired EntityManager em;
    @Autowired OrderService orderService; 
    @Autowired OrderRepository orderRepository;


    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        
        Book book = new Book();
        book.setName("시골 JPA");
        book.setPrice(10000);
        book.setStockQuantity(10);
        em.persist(book);
        
        int orderCount = 2;

        //when
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        //then
        Order getOrder = orderRepository.findOne(orderId);

        assertEquals( OrderStatus.ORDER, getOrder.getStatus());
        assertEquals( 1, getOrder.getOrderItems().size());
        assertEquals( 10000 * orderCount, getOrder.getTotalPrice());
        assertEquals( 8, book.getStockQuantity());
    
    }   

    @Test
    void testFindOrder() {
        //given

        //when

        //then
    }

    @Test
    void testOrder() {
        //given

        //when

        //then
    }



}
