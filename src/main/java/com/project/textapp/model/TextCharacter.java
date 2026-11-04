package com.project.textapp.model;

import java.util.List;

public class TextCharacter implements TextComponent {
    private final ElementType type;
    private final char value;

    public TextCharacter(ElementType type, char value) {
        this.type = type;
        this.value = value;
    }
    @Override
    public ElementType getType() {
        return type;
    }
    public char getValue()     {
        return value;
    }
    @Override
    public void add(TextComponent component){
        throw new UnsupportedOperationException("Characters and symbols can't contain other components");
    }
    @Override
    public void remove(TextComponent component){
        throw new UnsupportedOperationException("Characters and symbols can't and don't contain other components");
    }
    @Override
    public List<TextComponent> getChildren() {
        throw new UnsupportedOperationException("Characters and symbols can't and don't contain other components");
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TextCharacter other = (TextCharacter) obj;
        return value == other.value && type == other.type;
    }
    @Override
    public int hashCode() {
        return 31 * Character.hashCode(value) + type.hashCode();
    }
    public String restore() {
        return String.valueOf(value);
    }
}
