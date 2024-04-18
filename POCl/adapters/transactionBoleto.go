package adapters

import (
	"fmt"
	"pocl/core/port"
)

func FazerTransacaoBoleto(valor float64, transactionPort port.TransactionPort) bool {
	fmt.Println("entrou no FazerTransacaoBoleto")
	fmt.Println(transactionPort)
	return transactionPort.Transacionar(valor)
}
