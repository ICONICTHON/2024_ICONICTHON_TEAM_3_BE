package com.akofood.server.controller;

import com.akofood.server.dto.res.ApproveResponse;
import com.akofood.server.service.KakaoPayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import com.akofood.server.entity.OrderCreateForm;

import com.akofood.server.dto.res.ReadyResponse;


@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    @Value("${pay.pay-client}")
    private String pay_client;

    private final KakaoPayService kakaoPayService;

    @PostMapping("/pay/ready")
    public @ResponseBody ReadyResponse payReady(@RequestBody OrderCreateForm orderCreateForm) {

        String name = orderCreateForm.getName();
        int totalPrice = orderCreateForm.getTotalPrice();

        log.info("주문 상품 이름: " + name);
        log.info("주문 금액: " + totalPrice);

        // 카카오 결제 준비하기
        ReadyResponse readyResponse = kakaoPayService.payReady(name, totalPrice);
        // 세션에 결제 고유번호(tid) 저장
        SessionUtils.addAttribute("tid", readyResponse.getTid());
        log.info("결제 고유번호: " + readyResponse.getTid());

        return readyResponse;
    }

    @GetMapping("/pay/completed")
    public String payCompleted(@RequestParam("pg_token") String pgToken) {

        String tid = SessionUtils.getStringAttributeValue("tid");
        log.info("결제승인 요청을 인증하는 토큰: " + pgToken);
        log.info("결제 고유번호: " + tid);

        // 카카오 결제 요청하기
        ApproveResponse approveResponse = kakaoPayService.payApprove(tid, pgToken);

        // 결제 승인 응답 처리
        if (approveResponse != null && approveResponse.getTid() != null) {
            // 결제 승인 성공 시, 해당 결제 정보를 DB에 저장
            // 예시로 OrderService를 사용해 결제 정보 저장 처리
//            Order order = new Order();
//            order.setTid(approveResponse.getTid());
//            order.setAmount(approveResponse.getAmount().getTotal());
//            order.setPaymentMethod(approveResponse.getPaymentMethodType());
//            order.setStatus("COMPLETED");
//
//            // 결제 정보 저장
//            orderService.saveOrder(order);

            String externalUrl = pay_client;   // 다른 호스트로 리다이렉트
            return "redirect:" + externalUrl;  // 다른 호스트로 리다이렉트
        } else {
            // 결제 실패 시, 실패 페이지로 리다이렉트
//            String externalFailUrl = "https://www.external-site.com/pay/fail";  // 다른 호스트로 리다이렉트
//            return "redirect:" + externalFailUrl;  // 실패 페이지로 리다이렉트
            return null;
        }
    }
}