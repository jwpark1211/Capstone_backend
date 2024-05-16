package capstone.bookitty.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member {

    @Id @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String profileImg;
    private String email;
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Builder
    public Member(String name, String email, String password, String profileImg,
                  Gender gender, LocalDate birthDate){
        this.name =  name;
        this.email = email;
        this.profileImg = profileImg != null ? profileImg : "";
        this.gender = gender;
        this.password = password;
        this.birthDate = birthDate;
        this.createdAt = LocalDateTime.now();
        this.profileImg = "https://bookitty-bucket.s3.ap-northeast-2.amazonaws.com/Jiji.jpeg";
        this.grade = Grade.USER;
    }

    public void updateProfile(String profileImg){
        this.profileImg = profileImg;
    }

    public void deleteProfile(){
        this.profileImg = "https://bookitty-bucket.s3.ap-northeast-2.amazonaws.com/Jiji.jpeg";
    }
}
