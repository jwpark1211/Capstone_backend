package capstone.bookitty.domain.dto;

import capstone.bookitty.domain.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class CommentDTO {

    @Data
    public static class SaveRequest{
        @NotBlank(message = "Isbn is a required entry value.")
        private String isbn;
        @NotNull(message = "memberId is a required entry value.")
        private Long memberId;
        @NotEmpty(message = "content is a required entry value.")
        @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
        private String content;
    }

    @Data
    public static class UpdateRequest{
        @NotEmpty(message = "content is a required entry value.")
        @Size(min = 1, max = 100, message = "Name must be between 1 and 100 characters")
        private String content;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdResponse{
        private Long id;
        public static IdResponse of(Comment comment){
            return new IdResponse(comment.getId());
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InfoResponse{
        private Long id;
        private Long memberId;
        private String isbn;
        private String content;
        private int like_count;
        @DateTimeFormat(pattern = "yyyy-mm-dd'T'HH:mm:ss")
        private LocalDateTime createdAt;
        @DateTimeFormat(pattern = "yyyy-mm-dd'T'HH:mm:ss")
        private LocalDateTime modifiedAt;

        public static InfoResponse of(Comment comment, int like_count){
            return new InfoResponse(comment.getId(), comment.getMember().getId(),comment.getIsbn(),
                    comment.getContent(), like_count ,comment.getCreatedAt(),
                    comment.getModifiedAt());
        }

    }
}
