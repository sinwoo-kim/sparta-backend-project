package hello.core.member;

import core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberEntityServiceTest {

    MemberService memberService = new MemberServiceimpl(new MemoryMemberRespository());

    @Test
    void join() {
        // given
        MemberEntity member = new MemberEntity(1L,"memberA", Grade.VIP);

        //when
        memberService.join(member);
        MemberEntity findMember = memberService.findMember(1L);

        // then
        Assertions.assertThat(member).isEqualTo(findMember);

    }
}
