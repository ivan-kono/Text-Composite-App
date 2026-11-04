package com.project.textapp.model;

import java.util.List;

public interface TextComponent {
    public ElementType getType();
    public void add(TextComponent value);
    public void remove(TextComponent value);
    List<TextComponent> getChildren();
    String restore();
}
