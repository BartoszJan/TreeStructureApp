package com.bjanczak.service;

import com.bjanczak.model.Node;

import java.util.List;
import java.util.Optional;

public interface NodeService {

    /**
     * Get all nodes list
     *
     * @return list of all nodes List<Node>
     */
    public List<Node> findAll();

    /**
     * Find node by id
     *
     * @param id Long
     * @return node Optional<Node>
     */
    public Optional<Node> findOneById(Long id);

    /**
     * Save node
     *
     * @param node Node
     * @return saved node Node
     */
    public Node saveNode(Node node);
}
