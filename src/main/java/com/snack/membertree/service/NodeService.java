package com.snack.membertree.service;

import com.snack.membertree.aop.LogHandle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NodeService {
    @LogHandle
    Node add(Node parent, Node left, String title);

    @LogHandle
    void remove(Node node);

    @LogHandle
    void createTree(String rootTitle);

    @LogHandle
    List<Node> getNodes();

    @LogHandle
    List<Node> getChildren(Node node);

    @LogHandle
    List<Node> getOneLevelChildren(Node node);
}
