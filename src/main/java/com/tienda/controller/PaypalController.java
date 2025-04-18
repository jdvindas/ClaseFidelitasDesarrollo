package com.tienda.controller;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.tienda.services.ConstanteService;
import com.tienda.services.ItemService;
import com.tienda.services.impl.PaypalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/payment")
@Slf4j
public class PaypalController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ConstanteService constanteService;
    
    private final PaypalService paypalService;

    @GetMapping("/facturar")
    public RedirectView createPayment(@RequestParam("total") double total) {
        if (total > 0) {
            try {
                String urlPaypalCancel = constanteService.getConstantePorAtributo("urlPaypalCancel").getValor();
                String urlPaypalSuccess = constanteService.getConstantePorAtributo("urlPaypalSuccess").getValor();
                Payment payment = paypalService.createPayment(
                        total,
                        "USD",
                        "paypal",
                        "sale",
                        "Facturación en TechShop",
                        urlPaypalCancel,
                        urlPaypalSuccess);
                for (Links links : payment.getLinks()) {
                    if (links.getRel().equals("approval_url")) {
                        return new RedirectView(links.getHref());
                    }
                }
            } catch (PayPalRESTException e) {
                log.error("Error en pago: ", e);
            }
        }
        return new RedirectView("/payment/error");
    }

    @GetMapping("/success")
    public String paymentSuccess(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = paypalService.excecutePayment(paymentId, payerId);
            if (payment.getState().equals("approved")) {
                itemService.facturar();
                return "/paypal/pagoSuccess";
            }
        } catch (PayPalRESTException e) {
            log.error("Error en pago: ", e);
        }
        return "/paypal/pagoSuccess";
    }

    @GetMapping("/cancel")
    public String paymentCancel() {
        return "/paypal/pagoCancel";
    }

    @GetMapping("/error")
    public String paymentError() {
        return "/paypal/pagoError";
    }
}