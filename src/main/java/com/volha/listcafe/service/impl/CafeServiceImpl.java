package com.volha.listcafe.service.impl;

import com.volha.listcafe.exception.CafeNotFoundException;
import com.volha.listcafe.model.Address;
import com.volha.listcafe.model.Cafe;
import com.volha.listcafe.repository.CafeRepository;
import com.volha.listcafe.service.CafeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/*
 *Created on 03.08.2021
 */
@Service
@RequiredArgsConstructor
@Log
public class CafeServiceImpl implements CafeService {
    private static final int PAGE_SIZE = 10;
    private final CafeRepository cafeRepository;

    public List<Cafe> getCafes(int page, Sort.Direction sort, String name) {
        return cafeRepository.findAllCafes(PageRequest.of(page, PAGE_SIZE, Sort.by(sort, "id")),
                name);
    }

    public Optional<Cafe> getOneCafe(Long id) {
        return Optional.ofNullable(cafeRepository.findById(id)
                .orElseThrow(() -> new CafeNotFoundException(id)));
    }

    public Cafe addCafe(Cafe cafe) {
        Cafe newCafe = new Cafe();
        newCafe.setName(cafe.getName());
        newCafe.setAddress(Address.builder()
                .city(cafe.getAddress().getCity())
                .street(cafe.getAddress().getStreet())
                .number(cafe.getAddress().getNumber())
                .cafe(newCafe)
                .build());
        try {
            return cafeRepository.save(newCafe);
        } catch (Exception e) {
            return null;
        }
    }

    public Cafe updateCafe(Long id, Cafe cafe) {
        Optional<Cafe> cafeFromDb = Optional.ofNullable(cafeRepository.findById(id)
                .orElseThrow(() -> new CafeNotFoundException(id)));
        if (cafeFromDb.isPresent()) {
            Cafe cafeForEdit = cafeFromDb.get();
                    cafeForEdit.setName(cafe.getName());
                    cafeForEdit.setAddress(cafe.getAddress());
                    cafeForEdit.getAddress().setId(cafeFromDb.get().getId());
            return cafeRepository.save(cafeForEdit);
        } else {
            return null;
        }
    }

    public void deleteCafe(Long id) {
        cafeRepository.deleteById(id);
    }
}
