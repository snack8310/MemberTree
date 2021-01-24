package com.snack.membertree.service;

import lombok.Data;

@Data
public class Node {
    private int left;
    private int right;
    private String title;

    public Node(String title, int left, int right) {
        this.left = left;
        this.right = right;
        this.title = title;
    }

    public void print() {
        System.out.println("title:" + this.getTitle() + ";left:" + this.getLeft() + "；right:" + this.getRight());
    }
}
