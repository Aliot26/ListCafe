package com.volha.listcafe.dto;

import com.volha.listcafe.model.Address;
import com.volha.listcafe.model.Cafe;

import java.util.List;
import java.util.stream.Collectors;

/*
 *Created on 05.08.2021
 */
public class CafeDtoMapper {
    private CafeDtoMapper() {
    }

    public static List<CafeDto> mapToCafeDtos(List<Cafe> cafes){
        return cafes.stream()
                .map(CafeDtoMapper::mapToCafeDto)
                .collect(Collectors.toList());
    }

    private static CafeDto mapToCafeDto(Cafe cafe) {
        return CafeDto.builder()
                .id(cafe.getId())
                .name(cafe.getName())
                .build();
    }
}
