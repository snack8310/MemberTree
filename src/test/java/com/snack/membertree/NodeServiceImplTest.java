package com.snack.membertree;

import com.snack.membertree.service.Node;
import com.snack.membertree.service.NodeService;
import com.snack.membertree.service.NodeServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class NodeServiceImplTest {

    private NodeService ns;

    @Before
    public void setUp() throws Exception {
        ns = new NodeServiceImpl();
        ns.createTree("root");
        List<Node> pl = new LinkedList<>();
        pl.add(ns.getNodes().get(0));
        addLevelNodes(pl);
    }

    private void addLevelNodes(List<Node> parent) {
        List<Node> levelLefts = new LinkedList<>();
        for (Node p : parent) {
            List<Node> childrenNodes = addChildrenNodes(p);
            if (childrenNodes.size() == 0) return;
            levelLefts.addAll(childrenNodes);
        }
        addLevelNodes(levelLefts);
    }

    private List<Node> addChildrenNodes(Node parent) {
        List<Node> lefts = new LinkedList<>();
        Node left = null;
        for (int i = 1; i <= 5; i++) {
            if (ns.getNodes().size() == 100) break;
            left = ns.add(parent, left, parent.getTitle() + i);
            lefts.add(left);
        }
        return lefts;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        assertTrue(true);
    }

    @Test
    public void remove() {
    }

    @Test
    public void createTree() {
    }

    @Test
    public void getNodes() {
        assertTrue(ns.getNodes().size() == 100);

    }

    @Test
    public void getChildren() {
    }

    @Test
    public void getOneLevelChildren() {
    }
}