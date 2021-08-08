package com.volha.listcafe.service;

import com.volha.listcafe.dto.CafeDto;
import com.volha.listcafe.model.Cafe;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/*
 *Created on 03.08.2021
 */
public interface CafeService {
    List<Cafe> getCafes(int pageNumber, Sort.Direction sortDirection, String name);
    Optional<Cafe> getOneCafe(Long id);
    Cafe addCafe(Cafe cafe);
    Cafe updateCafe(Long id, Cafe cafe);
    void deleteCafe(Long id);
}
