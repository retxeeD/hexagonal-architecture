package controller

import (
	"POCL/application/domain"
	"POCL/application/port/input"
)

type transactionController struct {
	transactionUseCase input.TransactionUseCase
}

func NewTransactionController(
	transactionUseCase input.TransactionUseCase,
) *transactionController {
	return &transactionController{transactionUseCase: transactionUseCase}
}

func (tc *transactionController) Transacionar(valorEmConta float32, valorTransferido float32) float32 {
	transactionReqDomain := domain.TransactionReqDomain{
		ValorEmConta:     valorEmConta,
		ValorTransferido: valorTransferido,
	}
	resultado := tc.transactionUseCase.FazerTransacaoService(transactionReqDomain)

	return resultado
}
