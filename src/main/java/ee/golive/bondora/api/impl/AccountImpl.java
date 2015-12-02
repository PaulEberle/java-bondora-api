package ee.golive.bondora.api.impl;

import ee.golive.bondora.api.domain.MyInvestmentItem;
import ee.golive.bondora.api.domain.MyAccountBalance;
import ee.golive.bondora.api.domain.results.ApiResultList;
import ee.golive.bondora.api.domain.results.ApiResultSingle;
import ee.golive.bondora.api.operations.AccountOperations;
import org.springframework.core.ParameterizedTypeReference;

import java.util.List;

public class AccountImpl implements AccountOperations {

    private BondoraImpl api;

    public AccountImpl(BondoraImpl api) {
        this.api = api;
    }

    @Override
    public MyAccountBalance getAccountBalance() {
        ParameterizedTypeReference<ApiResultSingle<MyAccountBalance>> reference =
                new ParameterizedTypeReference<ApiResultSingle<MyAccountBalance>>() {};
        return (api.fetchObject(api.apiUrl("account/balance"), reference)).getPayload();
    }

    @Override
    public List<MyInvestmentItem> getInvestments() {
        ParameterizedTypeReference<ApiResultList<MyInvestmentItem>> reference =
                new ParameterizedTypeReference<ApiResultList<MyInvestmentItem>>() {};
        return (api.fetchObject(api.apiUrl("account/investments"), reference)).getPayload();
    }
}
