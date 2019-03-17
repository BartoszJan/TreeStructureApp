package com.bjanczak.controller;

import com.bjanczak.exception.NodeNotFoundException;
import com.bjanczak.model.Node;
import com.bjanczak.model.dto.NodeDto;
import com.bjanczak.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/copy_node", method = RequestMethod.POST)
    public NodeDto copyNode(@RequestBody NodeDto node) {
        return nodeService.copyNodeWithChildren(node.toEntity());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/node/{id}", method = RequestMethod.PUT)
    public NodeDto updateNode(@PathVariable(value = "id") Long id, @Valid @RequestBody NodeDto nodeDetails) {
        Node updatedNode = nodeService.findOneById(id).orElseThrow(() -> new NodeNotFoundException(id));
        updatedNode.setValue(nodeDetails.getValue());

        return nodeService.updateNode(updatedNode);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/node/{id}", method = RequestMethod.DELETE)
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long id) {
        Node deletedNode = nodeService.findOneById(id).orElseThrow(() -> new NodeNotFoundException(id));

        nodeService.deleteNode(deletedNode);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
