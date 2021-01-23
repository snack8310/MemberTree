package com.snack.membertree;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class NodeServiceImpl implements NodeService {

    private List<Node> nodes;
    private final String START = "start";
    private final String END = "end";

    @Override
    public Node add(Node parent, Node left, String title) {

        log.info("add" + START);
        int nl = parent.getLeft();
        int index = nodes.indexOf(parent) + 1;
        if (left != null) {
            nl = left.getRight();
            index = nodes.indexOf(left) + 1;
        }
        Node newNode = new Node(title, nl + 1, nl + 2);
        for (Node node : nodes) {
            if (node.getLeft() > nl) {
                node.setLeft(node.getLeft() + 2);
            }
            if (node.getRight() > nl) {
                node.setRight(node.getRight() + 2);
            }
        }
        nodes.add(index, newNode);
        log.info("add" + END);
        return newNode;
    }

    @Override
    public void remove(Node removeNode) {
        log.info("remove" + START);
        int nl = removeNode.getLeft();
        int nr = removeNode.getRight();
        for (Node node : nodes) {
            if (node.getLeft() > nl && node.getRight() < nr) {
                nodes.remove(node);
                continue;
            }
            if (node.getLeft() > nl) {
                node.setLeft(node.getLeft() - 2);
            }
            if (node.getRight() > nl) {
                node.setRight(node.getRight() - 2);
            }
        }
        nodes.remove(removeNode);
        log.info("remove" + END);
        return;
    }

    @Override
    public void createTree(String rootTitle) {
        nodes = new LinkedList<>();
        Node node = new Node(rootTitle, 1, 2);
        nodes.add(node);
    }

    @Override
    public List<Node> getNodes() {
        return this.nodes;
    }

    @Override
    public List<Node> getChildren(Node currentNode) {
        log.info("getChildren" + START);
        List<Node> children = new LinkedList<>();
        int nl = currentNode.getLeft();
        int nr = currentNode.getRight();
        for (Node node : nodes) {
            if (node.getLeft() > nl && node.getRight() < nr) {
                children.add(node);
            }
        }
        log.info("getChildren" + END);
        return children;
    }

    @Override
    public List<Node> getOneLevelChildren(Node currentNode) {
        log.info("getOneLevelChildren" + START);
        List<Node> children = new LinkedList<>();
        int nl = currentNode.getLeft();
        int nr = currentNode.getRight();
        Node leftChild = null;
        for (Node node : nodes) {
            if (node.getLeft() <= nl || node.getRight() >= nr) {
                continue;
            }
            if (leftChild != null && node.getRight() < leftChild.getRight()) {
                continue;
            }
            leftChild = node;
            children.add(leftChild);
        }
        log.info("getOneLevelChildren" + END);
        return children;
    }
}
