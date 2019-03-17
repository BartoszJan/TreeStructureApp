package com.bjanczak.service.impl;

import com.bjanczak.exception.NodeNotFoundException;
import com.bjanczak.model.Node;
import com.bjanczak.model.dto.NodeDto;
import com.bjanczak.repository.NodeRepository;
import com.bjanczak.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public Optional<Node> findOneById(Long id) {
        return nodeRepository.findById(id);
    }

    @Override
    public NodeDto updateNode(Node node) {
        Node savedNode = saveNode(node);
        findLeafsAndUpdate(savedNode.getChildren());
        return new NodeDto(savedNode);
    }

    @Override
    public NodeDto createNode(NodeDto node) {
        Node parentNode = findOneById(node.getParentId())
                .orElseThrow(() -> new NodeNotFoundException(node.getParentId()));
        if (isLeaf(parentNode)) {
            parentNode.setValue(0L);
            saveNode(parentNode);
        }
        node.setValue(calculateLeafValue(node));
        return new NodeDto(saveNode(node.toEntity()));
    }

    @Override
    public void deleteNode(Node node) {
        Node deletedNodeParent = node.getParent();
        nodeRepository.delete(node);
        if (isLeaf(deletedNodeParent)) {
            deletedNodeParent.setValue(calculateLeafValue(new NodeDto(deletedNodeParent)));
            saveNode(deletedNodeParent);
        }
    }

    private Node saveNode(Node node) {
        return nodeRepository.save(node);
    }

    private void findLeafsAndUpdate(List<Node> nodes) {
        for (Node n : nodes) {
            if (isLeaf(n)) {
                n.setValue(calculateLeafValue(new NodeDto(n)));
                saveNode(n);
            } else
                findLeafsAndUpdate(n.getChildren());
        }
    }

    private Long calculateLeafValue(NodeDto nodeDto) {
        Long result = 0L;
        Optional<Node> parentNode = findOneById(nodeDto.getParentId());
        while (parentNode.isPresent()) {
            result += parentNode.get().getValue();
            parentNode = Optional.ofNullable(parentNode.get().getParent());
        }
        return result;
    }

    private boolean isLeaf(Node node) {
        return (node.getChildren() == null || node.getChildren().isEmpty()) && !isRoot(node);
    }

    private boolean isRoot(Node node) {
        return node.getParent() == null;
    }
}
