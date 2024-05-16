package capstone.bookitty.domain.dto.ResponseType;

import org.springframework.data.domain.Page;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ResponseCounter<T> extends BasicResponse {

    private int count;
    private T data;

    public ResponseCounter(T data) {
        this.data = data;
        if (data instanceof Page) {
            this.count = (int) ((Page<?>) data).getTotalElements();
        }
        else if (data instanceof List) {
            this.count = ((List<?>) data).size();
        } else {
            this.count = 1;
        }
    }
}
