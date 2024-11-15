package com.akofood.server.controller;

import com.akofood.server.dto.req.MealVoucherCreateRequest;
import com.akofood.server.dto.req.MealVoucherRequest;
import com.akofood.server.dto.res.MealVoucherDetailResponse;
import com.akofood.server.dto.res.MealVoucherResponse;
import com.akofood.server.dto.res.QrRecognizeResponse;
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

    @GetMapping("/users/{user_id}")
    public ResponseEntity<List<MealVoucherDetailResponse>> getMealVouchersByUserId(@PathVariable Long user_id) {
        List<MealVoucherDetailResponse> mealVouchers = mealVoucherService.getMealVouchersByUserId(user_id);
        return ResponseEntity.ok(mealVouchers);
    }

//    @PostMapping
//    public MealVoucherResponse createMealVoucher(@RequestBody MealVoucherRequest request) {
//        return mealVoucherService.createMealVoucher(request);
//    }

    @GetMapping("/qr")
    public QrRecognizeResponse putQrRecognize(@RequestParam String unique_identifier){
        return mealVoucherService.putQrRecognize(unique_identifier);
    }

    @PostMapping
    public MealVoucherResponse createMealVoucher(@RequestBody MealVoucherCreateRequest request) {
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