package com.snack.membertree;

import com.snack.membertree.aop.Aop;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
        NodeService target = new NodeServiceImpl();
        Aop aop = new Aop(target);
        NodeService ns = (NodeService) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), aop);
        ns.createTree("root");
        print(ns.getNodes());
        List<Node> nodes = ns.getNodes();
        Node root = nodes.get(0);
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

    public static void print(List<Node> nodes) {
        for (Node node : nodes) {
            node.print();
        }
        System.out.println("------------------------------------------");

    }
}
