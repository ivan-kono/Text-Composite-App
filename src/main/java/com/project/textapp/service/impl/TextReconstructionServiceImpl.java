package com.project.textapp.service.impl;
import java.util.StringJoiner;

import com.project.textapp.model.AbstractTextComponent;
import com.project.textapp.model.impl.TextBranch;
import com.project.textapp.service.TextReconstructionService;

public class TextReconstructionServiceImpl implements TextReconstructionService {
    public String reconstruct(TextBranch document) {
        StringJoiner paragraphs = new StringJoiner(System.lineSeparator() + System.lineSeparator());
        for (AbstractTextComponent paragraph : document.getChildren()) {
            StringJoiner sentences = new StringJoiner(" ");
            for (AbstractTextComponent sentence : paragraph.getChildren()) {
                StringJoiner lexemes = new StringJoiner(" ");
                for (AbstractTextComponent lexeme : sentence.getChildren()) lexemes.add(lexeme.getValue());
                sentences.add(lexemes.toString());
            }
            paragraphs.add(sentences.toString());
        }
        return paragraphs.toString();
    }
}
