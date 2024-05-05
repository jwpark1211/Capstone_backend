package capstone.bookitty.domain.entity;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Grade {
    ADMIN("관리자"),
    USER("사용자")
    ;

    private final String grade;
}
