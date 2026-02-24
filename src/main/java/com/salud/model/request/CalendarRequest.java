package com.salud.model.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalendarRequest {
    private String start;
    private String end;
}
