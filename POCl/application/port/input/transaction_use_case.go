package input

import "POCL/application/domain"

type TransactionUseCase interface {
	FazerTransacaoService(domain.TransactionReqDomain) float32
}
