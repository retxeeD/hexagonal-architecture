package output

import "POCL/application/domain"

type transactionPix struct{}

func NewTransactionPix() *transactionPix {
	return &transactionPix{}
}

func (tp *transactionPix) FazerTransacaoPort(entrada domain.TransactionReqDomain) float32 {
	return entrada.ValorEmConta - entrada.ValorTransferido
}
