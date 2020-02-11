package com.ecommerce.service;

import com.ecommerce.entity.BankSlip;

import java.util.Date;

public interface BankSlipService {

    public void fillPaymentWithBankSlip(final BankSlip bankSlip, final Date purchaseInstant);
}
