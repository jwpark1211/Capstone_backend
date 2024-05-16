package capstone.bookitty.domain.dto;

import capstone.bookitty.domain.annotation.ValidScore;
import capstone.bookitty.domain.entity.Star;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class StarDTO {
    @Data
    public static class SaveRequest{
        @NotBlank(message = "Isbn is a required entry value.")
        private String isbn;
        @NotNull
        private Long memberId;
        @ValidScore
        private double score;
    }

    @Data
    public static class UpdateRequest{
        @NotNull
        @ValidScore
        private double score;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdResponse{
        private Long id;
        public static IdResponse of(Star star){
            return new IdResponse(star.getId());
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InfoResponse{
        private Long id;
        private Long memberId;
        private String isbn;
        private double score;
        @DateTimeFormat(pattern = "yyyy-mm-dd'T'HH:mm:ss")
        private LocalDateTime createdAt;
        @DateTimeFormat(pattern = "yyyy-mm-dd'T'HH:mm:ss")
        private LocalDateTime modifiedAt;

        public static InfoResponse of(Star star){
            return new InfoResponse(star.getId(), star.getMember().getId(),star.getIsbn(),
                    star.getScore(),star.getCreatedAt(),star.getModifiedAt());
        }
    }
}
