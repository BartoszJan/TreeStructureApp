package com.bjanczak.service.impl;

import com.bjanczak.model.Node;
import com.bjanczak.repository.NodeRepository;
import com.bjanczak.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    @Override
    public List<Node> findAll() {
        return nodeRepository.findAll();
    }

    @Override
    public Optional<Node> findOneById(Long id) {
        return nodeRepository.findById(id);
    }

    @Override
    public Node saveNode(Node node) {
        return nodeRepository.save(node);
    }
}
