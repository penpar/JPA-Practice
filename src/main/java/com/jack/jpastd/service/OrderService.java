package com.jack.jpastd.service;

import java.util.List;

import com.jack.jpastd.domain.Delivery;
import com.jack.jpastd.domain.Member;
import com.jack.jpastd.domain.Order;
import com.jack.jpastd.domain.OrderItem;
import com.jack.jpastd.domain.OrderSearch;
import com.jack.jpastd.domain.item.Item;
import com.jack.jpastd.repository.ItemRepository;
import com.jack.jpastd.repository.MemberRepository;
import com.jack.jpastd.repository.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        
        //인티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //
        order.cancel();
    }
}
