package com.project.textapp.model;

public interface AbstractTextComponent {
    public ElementType getType();
    public String getValue();
    public void setValue(String value);
}
