package com.akofood.server.controller;

import com.akofood.server.dto.res.UserResponse;
import com.akofood.server.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.akofood.server.entity.User;
import com.akofood.server.entity.Order;
import com.akofood.server.service.UserService;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;
    private final OrderService orderService;

    @GetMapping("/order")
    public String order(@RequestParam(name = "message", required = false) String message,
                        @RequestParam(name = "orderUid", required = false) String id,
                        Model model) {

        model.addAttribute("message", message);
        model.addAttribute("orderUid", id);

        return "order";
    }

    @PostMapping("/order")
    public String autoOrder(RedirectAttributes redirectAttributes) {
        User user=userService.autoRegister();
        Order order=orderService.autoOrder(user);

        String message = "주문 실패";
        if(order != null) {
            message = "주문 성공";
        }

        redirectAttributes.addAttribute("message", message);
        redirectAttributes.addAttribute("orderUid", order.getOrderUid());

        return "redirect:/order"; //주문으로 돌아감
    }
}
