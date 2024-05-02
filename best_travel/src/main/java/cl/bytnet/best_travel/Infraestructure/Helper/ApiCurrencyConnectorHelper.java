package cl.bytnet.best_travel.Infraestructure.Helper;


import cl.bytnet.best_travel.Infraestructure.DTO.CurrencyDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Currency;

@Component
public class ApiCurrencyConnectorHelper {

    private final WebClient currencywebClient;

    //Value no es compatible con Lombok
    @Value(value = "${api.from-currency}")
    private String fromCurrency;
    @Value(value = "${api.amount-currency}")
    private String amountCurrency;

    private static final String FROM_CURRENCY_QUERYPARAM="from={from}";
    private static final String  TO_CURRENCY_QUERYPARAM="&to={to}";
    private static final String  AMOUNT_CURRENCY_QUERYPARAM="&amount={amount}";
    private static final  String CURRENCY_PATH="/exchangerates_data/convert";

    public ApiCurrencyConnectorHelper(WebClient currencywebClient) {
        this.currencywebClient = currencywebClient;
    }

    public CurrencyDTO getCurrency(Currency currency){
        var current=this.currencywebClient
                .get()
                .uri(uri->
                        uri.path(CURRENCY_PATH)
                                .query(FROM_CURRENCY_QUERYPARAM)
                                .query(TO_CURRENCY_QUERYPARAM)
                                .query(AMOUNT_CURRENCY_QUERYPARAM)
                                .build(fromCurrency,currency.getCurrencyCode(),amountCurrency))
                .retrieve()
                .bodyToMono(CurrencyDTO.class)
                .block();
        return current;



    }
}
