package com.dollarbarberhouse.backend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {
    @Id
    private Long id;
    @Column
    private String username;
    @Column
    private String password;
}
