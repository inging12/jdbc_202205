package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repo = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        //c
        Member member = new Member("memberVO", 10000);
        repo.save(member);

        //r
        Member findMember = repo.findById(member.getMemberId());
        log.info("findMember={}", findMember);
        Assertions.assertThat(findMember).isEqualTo(member);

        //u 10000 -> 20000
        repo.update(member.getMemberId(), 20000);
        Member updateMember = repo.findById(member.getMemberId());
        assertThat(updateMember.getMoney()).isEqualTo(20000);

        //d
        repo.delete(member.getMemberId());
        assertThatThrownBy(() -> repo.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }

}
