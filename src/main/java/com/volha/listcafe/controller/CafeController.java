package com.volha.listcafe.controller;

import com.volha.listcafe.dto.CafeDto;
import com.volha.listcafe.model.Cafe;
import com.volha.listcafe.service.CafeService;
import com.volha.listcafe.service.impl.CafeServiceImpl;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Preconditions;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static com.volha.listcafe.dto.CafeDtoMapper.mapToCafeDtos;

/*
 *Created on 03.08.2021
 */
@RestController
@Api("ApiController for Application")
@RequiredArgsConstructor
@RequestMapping("api/v2")
public class CafeController {
    private final CafeServiceImpl cafeService;

    @GetMapping("/cafes")
    public ResponseEntity<List<Cafe>> getCafes(@RequestParam(defaultValue = "0") Integer pageNumber,
                                                  @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection,
                                                  @RequestParam(required = false, value="name") String  name) {

        return new ResponseEntity<>(cafeService.getCafes(pageNumber, sortDirection, name),
                HttpStatus.OK);
    }

    @GetMapping("/cafes/{id}")
    public Optional<Cafe> getOneCafe(@PathVariable Long id) {
        return cafeService.getOneCafe(id);
    }

    @PostMapping("/cafes")
    public ResponseEntity<Cafe> addCafe(@RequestBody Cafe cafe) {
        try {
            Cafe addedCafe = cafeService.addCafe(cafe);
            return new ResponseEntity<>(addedCafe, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cafes/{id}")
    public ResponseEntity<Cafe> editCafe(@PathVariable Long id, @RequestBody Cafe cafe) {
        try {
            Cafe updatedCafe = cafeService.updateCafe(id, cafe);
            return new ResponseEntity<>(updatedCafe, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cafes/{id}")
    public ResponseEntity<HttpStatus> deleteCafe(@PathVariable Long id) {
        try {
            cafeService.deleteCafe(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
