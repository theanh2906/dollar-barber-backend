package com.dollarbarberhouse.backend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Events {
    private String id;
    private String allDay;
    private String end;
    private String start;
    private String title;
}
