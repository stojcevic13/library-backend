package demo.simple.library.model.dto.request;

import demo.simple.library.model.entity.request.RequestStatus;
import lombok.Data;

@Data
public class RequestDTOResponse {

    private Integer id;
    private RequestStatus status;
    private String book;
    private String username;
    private String firstName;
    private String lastName;

}
