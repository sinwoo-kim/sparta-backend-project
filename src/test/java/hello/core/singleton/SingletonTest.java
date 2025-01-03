package hello.core.singleton;

import core.AppConfig;
import core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {
    @Test
    @DisplayName("싱글톤 컨테이너")
    void springContainer() {
        /*AppConfig appConfig = new AppConfig();*/
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);

        //참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);
    }
//    @Test
//    @DisplayName("싱글톤 사용")
//            void singletoneServiceTest() {
//    SingleToneService singleToneService1 = SingleToneService.getInstance();
//    SingleToneService singleToneService2 = SingleToneService.getInstance();
//
//        System.out.println("singleToneService1 = " + singleToneService1);
//        System.out.println("singleToneService2 = " + singleToneService2);
//    }
}
