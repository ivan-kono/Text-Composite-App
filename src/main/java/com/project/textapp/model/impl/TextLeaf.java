package com.project.textapp.model.impl;

import com.project.textapp.model.ElementType;
import com.project.textapp.model.AbstractTextComponent;

public class TextLeaf implements AbstractTextComponent {
    private final ElementType type;
    private String value;

    public TextLeaf(ElementType type, String value) {
        this.type = type;
        this.value = value;
    }

    public ElementType getType() { return type; }
    public String getValue() { return value; }
    public void setValue(String value) { this.value = value; }
}
