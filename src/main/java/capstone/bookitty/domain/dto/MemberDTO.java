package capstone.bookitty.domain.dto;

import capstone.bookitty.domain.entity.Gender;
import capstone.bookitty.domain.entity.Member;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class MemberDTO {
    @Data
    public static class SaveRequest {
        @NotEmpty
        private String email;
        @NotEmpty
        private String password;
        private Gender gender;
        @DateTimeFormat(pattern = "yyyy-mm-dd")
        private LocalDate birthdate;
        private String name;
    }

    @Data
    public static class LoginRequest {
        @NotEmpty
        private String email;
        @NotEmpty
        private String password;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdResponse{
        private Long id;
        public static IdResponse of (Member member){
            return new IdResponse((member.getId()));
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BoolResponse{
        private boolean isTrue;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InfoResponse{
        private Long id;
        private String email;
        private String profileImg;
        private String name;
        private Gender gender;
        @DateTimeFormat(pattern="yyyy-mm-dd")
        private LocalDate birthDate;

        public static InfoResponse of(Member member){
            return new InfoResponse(member.getId(), member.getEmail(), member.getProfileImg(),
                    member.getName(), member.getGender(), member.getBirthDate());
        }
    }
}
