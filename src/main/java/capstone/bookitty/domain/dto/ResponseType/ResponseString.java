package capstone.bookitty.domain.dto.ResponseType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseString extends BasicResponse{
    private String actionMsg;

    public ResponseString(String actionMsg) {
        this.actionMsg = actionMsg;
    }
}
