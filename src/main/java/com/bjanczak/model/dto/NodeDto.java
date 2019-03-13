package com.bjanczak.model.dto;

import com.bjanczak.model.Node;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class NodeDto {

    private Long id;
    private Long value;
    private List<NodeDto> children;

    public NodeDto(Node node) {
        this.id = node.getId();
        this.value = node.getValue();
        this.children = node.getChildren() != null
                ? node.getChildren().stream().map(NodeDto::new).collect(Collectors.toList())
                : null;
    }

    public Node toEntity(NodeDto nodeDto) {
        Node node = new Node();
        node.setId(nodeDto.getId());
        node.setParent(null);
        node.setValue(nodeDto.getValue());
        return node;
    }
}
