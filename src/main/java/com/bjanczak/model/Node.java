package com.bjanczak.model;

import lombok.AllArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "node")
@AllArgsConstructor
@Setter
public class Node {

    private Long id;
    private Long value;
    private Node parent;
    private List<Node> childrens;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    @Column(name = "value")
    public Long getValue() {
        return value;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent")
    public Node getParent() {
        return parent;
    }

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    public List<Node> getChildrens() {
        return childrens;
    }
}
