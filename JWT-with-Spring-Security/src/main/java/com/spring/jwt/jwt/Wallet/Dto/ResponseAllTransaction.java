package com.spring.jwt.jwt.Wallet.Dto;

import com.spring.jwt.Wallet.Dto.TransactionDTO;
import lombok.Data;

import java.util.List;
@Data
public class ResponseAllTransaction {
    private String message;
    private List<TransactionDTO> list;
    private String exception;

    public ResponseAllTransaction(String message){
        this.message=message;
    }
}

