package com.akofood.server.controller;

import com.akofood.server.dto.req.MealVoucherRequest;
import com.akofood.server.dto.res.MealVoucherResponse;
import com.akofood.server.entity.MealVoucher;
import com.akofood.server.service.MealVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meal-vouchers")
public class MealVoucherController {

    @Autowired
    private MealVoucherService mealVoucherService;

    @GetMapping
    public List<MealVoucherResponse> getAllMealVouchers() {
        return mealVoucherService.getAllMealVouchers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealVoucherResponse> getMealVoucherById(@PathVariable Long id) {
        return mealVoucherService.getMealVoucherById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public MealVoucherResponse createMealVoucher(@RequestBody MealVoucherRequest request) {
        return mealVoucherService.createMealVoucher(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealVoucherResponse> updateMealVoucher(@PathVariable Long id, @RequestBody MealVoucherRequest request) {
        return ResponseEntity.ok(mealVoucherService.updateMealVoucher(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealVoucher(@PathVariable Long id) {
        mealVoucherService.deleteMealVoucher(id);
        return ResponseEntity.noContent().build();
    }
}