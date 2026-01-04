package com.proyecto.proyecto.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;

@Service
public class MercadoPagoService {

    public String createPayment(Double amount, String description) throws Exception {

        PreferenceItemRequest item =
                PreferenceItemRequest.builder()
                        .title(description)
                        .quantity(1)
                        .unitPrice(BigDecimal.valueOf(amount))
                        .build();

        PreferenceRequest request =
                PreferenceRequest.builder()
                        .items(List.of(item))
                        .build();

        PreferenceClient client = new PreferenceClient();
        Preference preference = client.create(request);

        return preference.getInitPoint();
    }
}
