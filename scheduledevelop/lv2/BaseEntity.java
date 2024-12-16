package scheduledevelop.lv2;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class) // 생성, 수정 시점 기록 자동 처리
@MappedSuperclass // 자식 클래스가 매핑 정보, 필드를 상속 받는다.
@Getter
public class BaseEntity {

    @CreatedDate
    private LocalDateTime createAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;
}
