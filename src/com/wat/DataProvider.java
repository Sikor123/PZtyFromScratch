package com.wat;

import com.wat.dto.EventDto;
import com.wat.dto.JwtTokenDto;
import lombok.Data;

import java.util.List;

@Data
public class DataProvider {
    public static JwtTokenDto jwtTokenDto;
    public static List<EventDto> events;
}
