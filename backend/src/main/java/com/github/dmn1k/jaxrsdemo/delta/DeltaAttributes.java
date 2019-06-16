package com.github.dmn1k.jaxrsdemo.delta;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeltaAttributes {
    private Boolean underline;
    private Boolean italic;
    private Boolean bold;
    private DeltaListType list;
    private Integer indent;

    public DeltaAttributes() {
    }

    @JsonCreator
    public DeltaAttributes(@JsonProperty("underline") Boolean underline,
                           @JsonProperty("italic") Boolean italic,
                           @JsonProperty("bold") Boolean bold,
                           @JsonProperty("list") DeltaListType list,
                           @JsonProperty("indent") Integer indent) {
        this.underline = underline;
        this.italic = italic;
        this.bold = bold;
        this.list = list;
        this.indent = indent;
    }

    public Boolean getUnderline() {
        return underline;
    }

    public Boolean getItalic() {
        return italic;
    }

    public Boolean getBold() {
        return bold;
    }

    public DeltaListType getList() {
        return list;
    }

    public Integer getIndent() {
        return indent;
    }

    @Override
    public String toString() {
        List<String> result = new ArrayList<>();

        if(underline != null && underline){
            result.add("underline");
        }

        if(italic != null && italic){
            result.add("italic");
        }

        if(bold != null && bold){
            result.add("bold");
        }

        if(list != null){
            result.add("list: " + list.toString() + " [indent: " + (indent == null ? 0 : indent) + "]");
        }

        return result.stream().collect(Collectors.joining(", "));
    }
}
