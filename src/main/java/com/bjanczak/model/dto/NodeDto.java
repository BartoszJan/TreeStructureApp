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
    private Long parentId;
    private List<NodeDto> children;

    public NodeDto(Node node) {
        this.id = node.getId();
        this.value = node.getValue();
        this.parentId = node.getParent() != null
                ? node.getParent().getId()
                : null;
        this.children = node.getChildren() != null
                ? node.getChildren().stream().map(NodeDto::new).collect(Collectors.toList())
                : null;
    }

    public Node toEntity() {
        Node node = new Node();
        node.setId(this.getId());
        node.setParent(new Node(this.getParentId()));
        node.setValue(this.getValue());
        node.setChildren(this.children != null
                ? this.children.stream().map(NodeDto::toEntity).collect(Collectors.toList())
                : null);
        return node;
    }
}
