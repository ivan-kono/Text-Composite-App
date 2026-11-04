package com.project.textapp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextComposite implements TextComponent {
    private final ElementType type;
    private final List<TextComponent> children = new ArrayList<>();

    public TextComposite(ElementType type) {
        this.type = type;
    }
    @Override
    public ElementType getType() {
        return type;
    }
    @Override
    public List<TextComponent> getChildren() {
        return List.copyOf(children);
    }
    @Override
    public void add(TextComponent component){
        children.add(component);
    }
    @Override
    public void remove(TextComponent component){
        children.remove(component);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TextComposite other = (TextComposite) obj;
        return type == other.type && children.equals(other.children);
    }

    @Override
    public int hashCode() {
        return 31 * type.hashCode() + children.hashCode();
    }
    @Override
    public String restore() {
        StringBuilder builder = new StringBuilder();
        for (TextComponent child : children){
            builder.append(child.restore());
        }
        return builder.toString();
    }
}


