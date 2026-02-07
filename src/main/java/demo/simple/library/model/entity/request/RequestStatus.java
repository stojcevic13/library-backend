package demo.simple.library.model.entity.request;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;


public enum RequestStatus {
    PENDING,
    APPROVED,
    REJECTED
}
