package com.snack.membertree.controller;

import com.snack.membertree.service.Node;
import com.snack.membertree.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Controller
public class NodeController {
    @Autowired
    private NodeService ns;

    public void createNewTree() {
        ns.createTree("root");
        Node root = ns.getNodes().get(0);
        List<Node> tNs = new LinkedList();
        tNs.add(root);
        addNodesByLevel(5, 100, tNs);
        Node zhangsan = ns.add(root, null, "zhangsan");
        Node lisi = ns.add(root, null, "lisi");
        Node wangwu = ns.add(root, zhangsan, "wangwu");
        print(ns.getNodes());
        Node zhangsan1 = ns.add(zhangsan, null, "zhangsan1");
        Node zhangsan2 = ns.add(zhangsan, null, "zhangsan2");
        Node zhangsan3 = ns.add(zhangsan, zhangsan1, "zhangsan3");
        print(ns.getNodes());
        ns.remove(zhangsan2);
        print(ns.getNodes());
        List<Node> zhangsanChildren = ns.getChildren(zhangsan);
        print(zhangsanChildren);
        List<Node> rootChildren = ns.getChildren(root);
        print(rootChildren);
        List<Node> rootOneLevelChildren = ns.getOneLevelChildren(root);
        print(rootOneLevelChildren);
        List<Node> zhangsanOneLevelChildren = ns.getOneLevelChildren(zhangsan);
        print(zhangsanOneLevelChildren);
        List<Node> zhangsan3OneLevelChildren = ns.getOneLevelChildren(zhangsan3);
        print(zhangsan3OneLevelChildren);
    }

    private void print(List<Node> nodes) {
        for (Node node : nodes) {
            node.print();
        }
        System.out.println("------------------------------------------");

    }

    private void addLevelNodes(int childrenNum, int nodeNum, List<Node> parent) {
        List<Node> levelLefts = new ArrayList<>();
        for (Node p : parent) {
            List<Node> childrenNodes = addChildrenNodes(childrenNum, nodeNum, p);
            if (childrenNodes.size() == 0) {
                return;
            }
            levelLefts.addAll(childrenNodes);
        }
        addLevelNodes(childrenNum, nodeNum, levelLefts);
    }

    private List<Node> addChildrenNodes(int childrenNum, int nodeNum, Node parent) {
        List<Node> lefts = new ArrayList<>();
        Node left = null;
        for (int i = 1; i <= childrenNum; i++) {
            if (ns.getNodes().size() >= nodeNum) {
                break;
            }
            left = ns.add(parent, left, parent.getTitle() + i);
            lefts.add(left);
        }
        return lefts;
    }

    private void addNodesByLevel(int childrenNum, int nodeNum, List<Node> parent) {
        addLevelNodes(childrenNum, nodeNum, parent);
    }

}
