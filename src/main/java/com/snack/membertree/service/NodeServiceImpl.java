package com.snack.membertree.service;

import com.snack.membertree.service.dao.NodeDB;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class NodeServiceImpl implements NodeService {

    @Override
    public Node add(Node parent, Node left, String title) {

        int nl = parent.getLeft();
        int index = NodeDB.nodes.indexOf(parent) + 1;
        if (left != null) {
            nl = left.getRight();
            index = NodeDB.nodes.indexOf(left) + 1;
        }
        Node newNode = new Node(title, nl + 1, nl + 2);
        for (Node node : NodeDB.nodes) {
            if (node.getLeft() > nl) {
                node.setLeft(node.getLeft() + 2);
            }
            if (node.getRight() > nl) {
                node.setRight(node.getRight() + 2);
            }
        }
        NodeDB.nodes.add(index, newNode);
        return newNode;
    }

    @Override
    public void remove(Node removeNode) {
        int nl = removeNode.getLeft();
        int nr = removeNode.getRight();
        for (Node node : NodeDB.nodes) {
            if (node.getLeft() > nl && node.getRight() < nr) {
                NodeDB.nodes.remove(node);
                continue;
            }
            if (node.getLeft() > nl) {
                node.setLeft(node.getLeft() - 2);
            }
            if (node.getRight() > nl) {
                node.setRight(node.getRight() - 2);
            }
        }
        NodeDB.nodes.remove(removeNode);
        return;
    }

    @Override
    public void createTree(String rootTitle) {
        NodeDB.nodes = new LinkedList<>();
        Node node = new Node(rootTitle, 1, 2);
        NodeDB.nodes.add(node);
    }

    @Override
    public List<Node> getNodes() {
        return NodeDB.nodes;
    }

    @Override
    public List<Node> getChildren(Node currentNode) {
        List<Node> children = new LinkedList<>();
        int nl = currentNode.getLeft();
        int nr = currentNode.getRight();
        for (Node node : NodeDB.nodes) {
            if (node.getLeft() > nl && node.getRight() < nr) {
                children.add(node);
            }
        }
        return children;
    }

    @Override
    public List<Node> getOneLevelChildren(Node currentNode) {
        List<Node> children = new LinkedList<>();
        int nl = currentNode.getLeft();
        int nr = currentNode.getRight();
        Node leftChild = null;
        for (Node node : NodeDB.nodes) {
            if (node.getLeft() <= nl || node.getRight() >= nr) {
                continue;
            }
            if (leftChild != null && node.getRight() < leftChild.getRight()) {
                continue;
            }
            leftChild = node;
            children.add(leftChild);
        }
        return children;
    }
}
