package main

import (
	"fmt"
	"pocl/adapters"
	"pocl/core/port"
)

func main() {
	fmt.Println("Start program.")
	var tp port.TransactionPort
	fmt.Println(tp)
	fmt.Println(adapters.FazerTransacaoBoleto(100.0, tp))
	fmt.Println(adapters.FazerTransacaoPix(100.0, tp))
}
