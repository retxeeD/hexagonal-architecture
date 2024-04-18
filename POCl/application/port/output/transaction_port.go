package output

import "POCL/application/domain"

type TransactionPort interface {
	FazerTransacaoPort(domain.TransactionReqDomain) float32
}
