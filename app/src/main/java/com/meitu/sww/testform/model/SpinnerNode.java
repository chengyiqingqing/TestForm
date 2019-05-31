package com.meitu.sww.testform.model;

import java.util.ArrayList;

/**
 * 节点类：包含当前节点的信息及对应的节点列表
 * @author ShaoWenWen
 * @date 2019/5/30
 */
public class SpinnerNode {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private ArrayList<SpinnerNode> nodes;

    public void setNodes(ArrayList<SpinnerNode> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<SpinnerNode> getNodes() {
        return nodes;
    }

}
