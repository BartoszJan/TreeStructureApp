package com.bjanczak.controller;

import com.bjanczak.exception.NodeNotFoundException;
import com.bjanczak.model.Node;
import com.bjanczak.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    public List<Node> getAllNodes() {
        return nodeService.findAll();
    }

    @RequestMapping(value = "/node", method = RequestMethod.POST)
    public Node createNode(@Valid @RequestBody Node node) {
        return nodeService.saveNode(node);
    }

    @RequestMapping("/persons/{id}")
    public Node updateNode(@PathVariable(value = "id") Long id, @Valid @RequestBody Node nodeDetails) {
        Node updatedNode = nodeService.findOneById(id).orElseThrow(() -> new NodeNotFoundException(id));

        updatedNode.setValue(nodeDetails.getValue());
        updatedNode.setParent(updatedNode.getParent());

        return nodeService.saveNode(updatedNode);
    }
}
