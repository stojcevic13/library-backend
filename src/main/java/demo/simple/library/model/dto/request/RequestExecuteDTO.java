package demo.simple.library.model.dto.request;

import demo.simple.library.model.entity.request.RequestStatus;
import lombok.Data;

@Data
public class RequestExecuteDTO {

    private RequestStatus action;
}
