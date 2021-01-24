package com.snack.membertree;

import java.util.List;

public interface NodeService {
    Node add(Node parent, Node left, String title);

    void remove(Node node);

    void createTree(String rootTitle);

    List<Node> getNodes();

    List<Node> getChildren(Node node);

    List<Node> getOneLevelChildren(Node node);
}
