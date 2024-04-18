package controller

import (
	"POCL/application/domain"
	"POCL/application/port/input"
	"fmt"
)

type transactionControllerPix struct {
	transactionUseCase input.TransactionUseCase
}

func NewTransactionControllerPix(
	transactionUseCase input.TransactionUseCase,
) *transactionControllerPix {
	return &transactionControllerPix{transactionUseCase: transactionUseCase}
}

func (tc *transactionControllerPix) TransacionarPix(valorEmConta float32, valorTransferido float32) float32 {
	fmt.Println("Transacionar pix")
	transactionReqDomain := domain.TransactionReqDomain{
		ValorEmConta:     valorEmConta,
		ValorTransferido: valorTransferido,
	}
	resultado := tc.transactionUseCase.FazerTransacaoService(transactionReqDomain)

	return resultado
}
