package com.bjanczak.service;

import com.bjanczak.model.Node;
import com.bjanczak.model.dto.NodeDto;

import java.util.List;
import java.util.Optional;

public interface NodeService {

    /**
     * Get root with all children nodes
     *
     * @return list with root with all nodes List<NodeDto>
     */
    public List<NodeDto> getRootWithChildren();

    /**
     * Find node by id
     *
     * @param id Long
     * @return node Optional<NodeDto>
     */
    public Optional<Node> findOneById(Long id);

    /**
     * Update node values
     *
     * @param node Node
     * @return updated node NodeDto
     */
    public NodeDto updateNode(Node node);

    /**
     * Create new node
     *
     * @param node NodeDto
     * @return created node NodeDto
     */
    public NodeDto createNode(NodeDto node);

    /**
     * Delete node
     *
     * @param node Node
     */
    public void deleteNode(Node node);

    /**
     * Copy node
     *
     * @param node Node
     * @return nodeDto NodeDto
     */
    NodeDto copyNodeWithChildren(Node node);
}
