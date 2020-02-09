package com.wat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimetableMatchDto {
    private int lp;
    private String time;
    private String teamName;
    private String opponentName;
}
