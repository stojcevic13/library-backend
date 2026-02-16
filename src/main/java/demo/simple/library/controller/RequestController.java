package demo.simple.library.controller;

import demo.simple.library.model.dto.request.RequestCreateDTO;
import demo.simple.library.model.dto.request.RequestDTOResponse;
import demo.simple.library.model.dto.request.RequestExecuteDTO;
import demo.simple.library.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("requests")
public class RequestController {

    @Autowired
    private RequestService requestService;


    @GetMapping
    public ResponseEntity<List<RequestDTOResponse>> findAll() {
        List<RequestDTOResponse> requests = requestService.findPendingRequests();
        return ResponseEntity.ok(requests);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<RequestDTOResponse>> findByUserId(@PathVariable Integer userId) {
        List<RequestDTOResponse> requests = requestService.findByUserId(userId);
        return ResponseEntity.ok(requests);
    }

    @PostMapping
    public ResponseEntity<RequestDTOResponse> createNewRequest(@RequestBody RequestCreateDTO requestCreateDTO) {
        RequestDTOResponse request = requestService.createNewRequest(requestCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RequestDTOResponse> executeRequest(@PathVariable Integer id, @RequestBody RequestExecuteDTO requestExecuteDTO) {
        RequestDTOResponse request = requestService.executeRequest(id, requestExecuteDTO);
        return ResponseEntity.ok(request);
    }
}
