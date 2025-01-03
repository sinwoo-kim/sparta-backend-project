package hello.core.member;

import core.AppConfig;
import core.member.Grade;
import core.member.MemberService;
import core.order.OrderEntity;
import core.order.OrderService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import core.member.MemberEntity;

public class OrderServiceTest {
    AppConfig appConfig = new AppConfig();
    MemberService memberService = appConfig.memberService();
    OrderService orderService = appConfig.orderServiceMemoryFix();

    @Test
    void createOrder() {
        long memberId = 1L;
        MemberEntity member = new MemberEntity(memberId, "memberA", Grade.VIP);
        memberService.join(member); // 구현체로 접근

        OrderEntity order = orderService.createOrder(memberId,"itemA",10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

}
