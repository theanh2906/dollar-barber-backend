package com.dollarbarberhouse.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.*;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Documents extends BaseModel{
    @Id
    private Long id;
    @Column
    private String title;
    @Column
    private String content;

    @JsonIgnore
    @Override
    public List<String> getHeader() {
        return new ArrayList<>(Arrays.asList("id", "title", "content"));
    }
    @JsonIgnore
    @Override
    public Map<String, Object> getMap() {
        Map<String, Object> documentMap = new HashMap<>();
        documentMap.put("id", this.id);
        documentMap.put("title", this.title);
        documentMap.put("content", this.content);
        return documentMap;
    }
}
