package demo.simple.library.repository;

import demo.simple.library.model.entity.request.Request;
import demo.simple.library.model.entity.request.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {

    List<Request> findByStatus(RequestStatus status);

    List<Request> findByUserId(Integer userId);
}
