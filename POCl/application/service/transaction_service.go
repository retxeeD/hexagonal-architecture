package service

import (
	"POCL/application/domain"
	"POCL/application/port/output"
)

type transactionService struct {
	transactionPort output.TransactionPort
}

func NewtransactionService(transactionPort output.TransactionPort) *transactionService {
	return &transactionService{transactionPort: transactionPort}
}

func (ts *transactionService) FazerTransacaoService(transactionReqDomain domain.TransactionReqDomain) float32 {
	transactionDomainResponse := ts.transactionPort.FazerTransacaoPort(transactionReqDomain)
	return transactionDomainResponse
}
