package com.bjanczak.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
public class NodeNotFoundException extends RuntimeException{

    private Long nodeId;

    public NodeNotFoundException(Long nodeId) {
        super(String.format("Node with id: %s not found", nodeId));
        this.nodeId = nodeId;
    }
}
