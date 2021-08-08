package com.volha.listcafe.dto;

import com.volha.listcafe.model.Address;
import lombok.Builder;
import lombok.Getter;

/*
 *Created on 05.08.2021
 */
@Builder
@Getter
public class CafeDto {
    private Long id;
    private String name;
}
