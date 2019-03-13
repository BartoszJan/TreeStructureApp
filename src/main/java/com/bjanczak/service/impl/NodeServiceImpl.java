package com.bjanczak.service.impl;

import com.bjanczak.model.Node;
import com.bjanczak.model.dto.NodeDto;
import com.bjanczak.repository.NodeRepository;
import com.bjanczak.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    @Override
    public List<NodeDto> getRootWithChildren() {
        Node root = nodeRepository.findByParentId(null);
        return Collections.singletonList(new NodeDto(root));
    }

    @Override
    public Optional<NodeDto> findOneById(Long id) {
        Optional<Node> node = nodeRepository.findById(id);
        return node.map(n -> Optional.of(new NodeDto(n))).orElse(null);
    }

    @Override
    public void saveNode(NodeDto node) {
        nodeRepository.save(node.toEntity(node));
    }
}
