package com.art.schema;

import java.util.ArrayList;
import java.util.List;

public class MenuSchema {

    private String nodeCode;
    private String parentNodeCode;
    private String nodeLevel;
    private String nodeName;
    private String childFlag;
    private String nodeKey;
    private String runScript;
    private String nodeDescription;
    private String nodeSign;
    private String nodeOrder;
    private List<MenuSchema> children = new ArrayList<MenuSchema>();
    public String getNodeCode() {
        return nodeCode;
    }
    public void setNodeCode(String nodeCode) {
        this.nodeCode = nodeCode;
    }
    public String getParentNodeCode() {
        return parentNodeCode;
    }
    public void setParentNodeCode(String parentNodeCode) {
        this.parentNodeCode = parentNodeCode;
    }
    public String getNodeLevel() {
        return nodeLevel;
    }
    public void setNodeLevel(String nodeLevel) {
        this.nodeLevel = nodeLevel;
    }
    public String getNodeName() {
        return nodeName;
    }
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    public String getChildFlag() {
        return childFlag;
    }
    public void setChildFlag(String childFlag) {
        this.childFlag = childFlag;
    }
    public String getNodeKey() {
        return nodeKey;
    }
    public void setNodeKey(String nodeKey) {
        this.nodeKey = nodeKey;
    }
    public String getRunScript() {
        return runScript;
    }
    public void setRunScript(String runScript) {
        this.runScript = runScript;
    }
    public String getNodeDescription() {
        return nodeDescription;
    }
    public void setNodeDescription(String nodeDescription) {
        this.nodeDescription = nodeDescription;
    }
    public String getNodeSign() {
        return nodeSign;
    }
    public void setNodeSign(String nodeSign) {
        this.nodeSign = nodeSign;
    }
    public String getNodeOrder() {
        return nodeOrder;
    }
    public void setNodeOrder(String nodeOrder) {
        this.nodeOrder = nodeOrder;
    }
    public List<MenuSchema> getChildren() {
        return children;
    }
    public void addChildren(MenuSchema aMenu) {
	children.add(aMenu);
    }

}
