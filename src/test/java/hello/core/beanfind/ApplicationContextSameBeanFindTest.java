package hello.core.beanfind;

import core.member.MemberRespository;
import core.member.MemoryMemberRespository;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);





    @Configuration
    static class SameBeanConfig {
        @Bean
        public MemberRespository memberRepository1() {
            return new MemoryMemberRespository();
        }

        @Bean
        public MemberRespository memberRepository2() {
            return new MemoryMemberRespository();
        }
    }
}
