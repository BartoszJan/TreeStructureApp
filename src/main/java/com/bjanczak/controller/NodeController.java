package com.bjanczak.controller;

import com.bjanczak.exception.NodeNotFoundException;
import com.bjanczak.model.Node;
import com.bjanczak.model.dto.NodeDto;
import com.bjanczak.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/nodes", method = RequestMethod.GET)
    public List<NodeDto> getAllNodes() {
        return nodeService.getRootWithChildren();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/node", method = RequestMethod.POST)
    public NodeDto createNode(@RequestBody NodeDto node) {
        return nodeService.createNode(node);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/node/{id}", method = RequestMethod.PUT)
    public void updateNode(@PathVariable(value = "id") Long id, @Valid @RequestBody NodeDto nodeDetails) {
        NodeDto updatedNode = nodeService.findOneById(id).orElseThrow(() -> new NodeNotFoundException(id));

        updatedNode.setValue(nodeDetails.getValue());
        nodeService.saveNode(updatedNode);
    }
}
