package com.dollarbarberhouse.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.Lob;
import java.sql.Blob;
import java.util.*;

@Table("FILES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileModel extends BaseModel{
    @Id
    private Long id;
    @Column
    private String name;
    @Lob
    @Column("BYTE")
    private byte[] bytes;

    @JsonIgnore
    @Override
    public List<String> getHeader() {
        return new ArrayList<>(Arrays.asList("id", "name", "byte"));
    }
    @JsonIgnore
    @Override
    public Map<String, Object> getMap() {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("id", this.id);
        objectMap.put("name", this.name);
        objectMap.put("byte", this.bytes);
        return objectMap;
    }
}
