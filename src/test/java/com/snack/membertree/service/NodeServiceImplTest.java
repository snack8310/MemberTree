package com.snack.membertree.service;

import com.snack.membertree.config.MainConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MainConfig.class)
public class NodeServiceImplTest {

    @Autowired
    private NodeService ns;

    @Before
    public void setUp() throws Exception {
        ns.createTree("root");
        List<Node> pl = new LinkedList<>();
        pl.add(ns.getNodes().get(0));
        addNodesByLevel(10, 1000, pl);
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

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        Node p = ns.getNodes().get(500);
        Node newOne = ns.add(p, null, "i'm new one");
        assertTrue("i'm new one".equals(newOne.getTitle()));
    }

    @Test
    public void remove() {
        Node p = ns.getNodes().get(1);
        ns.remove(p);
        assertTrue(889 == ns.getNodes().size());
        assertTrue(10 == ns.getOneLevelChildren(ns.getNodes().get(0)).size());
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