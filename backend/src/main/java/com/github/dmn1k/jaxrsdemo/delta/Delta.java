package com.github.dmn1k.jaxrsdemo.delta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Delta {
    private List<DeltaInsertOperation> ops;

    @JsonCreator
    public Delta(@JsonProperty("ops") List<DeltaInsertOperation> ops) {
        this.ops = ops;
    }


    public List<DeltaInsertOperation> getOps() {
        return ops;
    }

    @JsonIgnore
    public List<DeltaInsertOperation> getNormalizedOps() {
        return mergeListOpsWithValue(createOpsForNewlines(ops));
    }

    private List<DeltaInsertOperation> createOpsForNewlines(List<DeltaInsertOperation> input) {
        List<DeltaInsertOperation> result = new ArrayList<>();
        for (DeltaInsertOperation operation : input) {
            StringBuilder currentString = new StringBuilder();
            for (char c : operation.getInsert().toCharArray()) {
                if ('\n' == c) {
                    String str = currentString.toString();
                    if (!str.isEmpty()) {
                        result.add(new DeltaInsertOperation(str, operation.getAttributes()));
                    }

                    result.add(new DeltaInsertOperation("\n", operation.getAttributes()));
                    currentString = new StringBuilder();
                } else {
                    currentString.append(c);
                }
            }

            String str = currentString.toString();
            if (!str.isEmpty()) {
                result.add(new DeltaInsertOperation(str, operation.getAttributes()));
            }
        }

        return result;
    }

    private List<DeltaInsertOperation> mergeListOpsWithValue(List<DeltaInsertOperation> input) {
        List<DeltaInsertOperation> result = new ArrayList<>();
        for (int i = 0; i < input.size() - 1; i++) {
            DeltaInsertOperation op1 = input.get(i);
            DeltaInsertOperation op2 = input.get(i + 1);

            // Listen-Attribut kommt immer nach dem Text als eigene Operation mit Newline als Wert
            if (op2.getAttributes().getList() != null) {
                DeltaInsertOperation merged = new DeltaInsertOperation(op1.getInsert() + op2.getInsert(), mergeAttributes(op1, op2));
                result.add(merged);
            } else if (op1.getAttributes().getList() == null) { // wenn op1 eine liste ist wurde sie bereits im letzten schleifendurchgang als op2 behandelt
                result.add(op1);
            }
        }

        DeltaInsertOperation lastOp = input.get(input.size() - 1);
        if (lastOp.getAttributes().getList() == null) {
            result.add(lastOp);
        }

        return result;
    }

    private DeltaAttributes mergeAttributes(DeltaInsertOperation op1, DeltaInsertOperation op2) {
        return new DeltaAttributes(op1.getAttributes().getUnderline(),
                op1.getAttributes().getItalic(), op1.getAttributes().getBold(),
                op2.getAttributes().getList(), op2.getAttributes().getIndent());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (DeltaInsertOperation op : getNormalizedOps()) {
            if (op.getAttributes().getList() != null) {
                int indent = op.getAttributes().getIndent() == null ? 0 : op.getAttributes().getIndent();
                sb.append(String.join("", Collections.nCopies(indent * 2, " ")));
                sb.append(" - ");
            }

            sb.append(op.getInsert());
        }

        return sb.toString();
    }
}
