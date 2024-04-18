package controller

import (
	"POCL/application/domain"
	"POCL/application/port/input"
	"fmt"
)

type transactionControllerBoleto struct {
	transactionUseCase input.TransactionUseCase
}

func NewTransactionControllerBoleto(
	transactionUseCase input.TransactionUseCase,
) *transactionControllerBoleto {
	return &transactionControllerBoleto{transactionUseCase: transactionUseCase}
}

func (tc *transactionControllerBoleto) TransacionarBoleto(valorEmConta float32, valorTransferido float32) float32 {
	fmt.Println("Transacionar boleto")
	transactionReqDomain := domain.TransactionReqDomain{
		ValorEmConta:     valorEmConta,
		ValorTransferido: valorTransferido,
	}
	resultado := tc.transactionUseCase.FazerTransacaoService(transactionReqDomain)

	return resultado
}
