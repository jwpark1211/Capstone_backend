package capstone.bookitty.domain.dto.ResponseType;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ResponseError extends BasicResponse{
    private String errorMsg;
    private String errorCode;

    public ResponseError(String errorMsg, String errorCode){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
