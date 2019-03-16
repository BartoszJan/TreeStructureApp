package com.bjanczak.service;

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
    public Optional<NodeDto> findOneById(Long id);

    /**
     * Save node
     *
     * @param node Node
     * @return saved node NodeDto
     */
    public void saveNode(NodeDto node);

    /**
     * Create new node
     *
     * @param node NodeDto
     * @return created node Node
     */
    public NodeDto createNode(NodeDto node);
}
