package com.ecommerce.service.impl;

import com.ecommerce.entity.BankSlip;
import com.ecommerce.service.BankSlipService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BankSlipServiceImpl implements BankSlipService {

    public void fillPaymentWithBankSlip(final BankSlip bankSlip, final Date purchaseInstant) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(purchaseInstant);
        calendar.add(Calendar.DAY_OF_MONTH, 7);

        bankSlip.setDueDate(calendar.getTime());
    }
}
