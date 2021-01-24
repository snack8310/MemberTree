package com.snack.membertree.service;

import com.snack.membertree.aop.LogHandle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NodeService {
    Node add(Node parent, Node left, String title);

    void remove(Node node);

    void createTree(String rootTitle);

    List<Node> getNodes();

    List<Node> getChildren(Node node);

    List<Node> getOneLevelChildren(Node node);
}
