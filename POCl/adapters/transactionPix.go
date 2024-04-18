package adapters

import (
	"fmt"
	"pocl/core/port"
)

func FazerTransacaoPix(valor float64, transactionPort port.TransactionPort) bool {
	fmt.Println("entrou no FazerTransacaoPix")
	return transactionPort.Transacionar(valor)
}
