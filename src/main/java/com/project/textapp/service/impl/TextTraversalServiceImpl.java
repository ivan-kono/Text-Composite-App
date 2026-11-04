package com.project.textapp.service.impl;
import java.util.ArrayList;
import java.util.List;

import com.project.textapp.model.ElementType;
import com.project.textapp.model.TextComponent;
import com.project.textapp.model.TextComposite;
import com.project.textapp.service.TextTraversalService;

public class TextTraversalServiceImpl implements TextTraversalService {
    public List<TextComponent> collect(TextComponent root, ElementType type) {
        List<TextComponent> result = new ArrayList<>();
        visit(root, type, result);
        return result;
    }
    private void visit(TextComponent node, ElementType type, List<TextComponent> result) {
        if (node.getType() == type) result.add(node);
        for (TextComponent child : node.getChildren()) visit(child, type, result);
    }
}
