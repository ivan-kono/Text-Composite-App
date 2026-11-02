package com.project.textapp.service.impl;
import java.util.ArrayList;
import java.util.List;

import com.project.textapp.model.ElementType;
import com.project.textapp.model.impl.TextBranch;
import com.project.textapp.service.TextTraversalService;

public class TextTraversalServiceImpl implements TextTraversalService {
    public List<TextBranch> collect(TextBranch root, ElementType type) {
        List<TextBranch> result = new ArrayList<>();
        visit(root, type, result);
        return result;
    }
    private void visit(TextBranch node, ElementType type, List<TextBranch> result) {
        if (node.getType() == type) result.add(node);
        for (TextBranch child : node.getChildren()) visit(child, type, result);
    }
}
