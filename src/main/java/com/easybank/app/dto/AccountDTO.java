package com.easybank.app.dto;

import com.easybank.app.model.User;

import java.math.BigInteger;

public record AccountDTO(BigInteger accountNumber, Double balance, User userId){}
