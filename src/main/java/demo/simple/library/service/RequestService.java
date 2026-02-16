package demo.simple.library.service;

import demo.simple.library.mapper.RequestMapper;
import demo.simple.library.model.dto.request.RequestCreateDTO;
import demo.simple.library.model.dto.request.RequestDTOResponse;
import demo.simple.library.model.dto.request.RequestExecuteDTO;
import demo.simple.library.model.entity.book.Book;
import demo.simple.library.model.entity.request.Request;
import demo.simple.library.model.entity.request.RequestStatus;
import demo.simple.library.model.entity.user.User;
import demo.simple.library.repository.BookRepository;
import demo.simple.library.repository.RequestRepository;
import demo.simple.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    private RequestMapper requestMapper = RequestMapper.INSTANCE;


    public List<RequestDTOResponse> findPendingRequests() {
        return requestRepository.findByStatus(RequestStatus.PENDING).stream().map(requestMapper::toDTOResponse).toList();
    };

    public List<RequestDTOResponse> findByUserId(Integer userId) {
        return requestRepository.findByUserId(userId).stream().map(requestMapper::toDTOResponse).toList();
    }

    public RequestDTOResponse createNewRequest(RequestCreateDTO requestCreateDTO) {
        Request request = new Request();

        User user = userRepository.findById(requestCreateDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found!"));
        Book book = bookRepository.findById(requestCreateDTO.getBookId()).orElseThrow(() -> new RuntimeException("Book not found!"));

        request.setUser(user);
        request.setBook(book);
        request.setStatus(RequestStatus.PENDING);

        requestRepository.save(request);

        return requestMapper.toDTOResponse(request);
    }


    public RequestDTOResponse executeRequest(Integer id, RequestExecuteDTO requestExecuteDTO) {
        Request request = requestRepository.findById(id).orElseThrow(() -> new RuntimeException("Request not found!"));

        request.setStatus(requestExecuteDTO.getAction());
        requestRepository.save(request);

        return requestMapper.toDTOResponse(request);
    }
}
