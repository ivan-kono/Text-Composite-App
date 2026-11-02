package com.project.textapp.model.impl;

import com.project.textapp.model.ElementType;
import com.project.textapp.model.AbstractTextComponent;

import java.util.ArrayList;
import java.util.List;

public class TextBranch implements AbstractTextComponent {
    private final ElementType type;
    private String value;
    private final List<AbstractTextComponent> children;

    public TextBranch(ElementType type, String value) {
        this.type = type;
        this.value = value;
        this.children = new ArrayList<>();
    }
    public ElementType getType() { return type; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
    public List<AbstractTextComponent> getChildren() { return children; }
    public void addComponent(AbstractTextComponent component){
        children.add(component);
    }
    public void removeComponent(AbstractTextComponent component){
        children.remove(component);
    }
}
