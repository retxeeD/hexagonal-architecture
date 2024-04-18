package main

import (
	"POCL/adapter/input/controller"
	"POCL/application/port/output"
	"POCL/application/service"
	"fmt"
)

func main() {
	fmt.Println("start hexagonal test")
	transactionPort := output.NewTransactionPix()
	transactionService := service.NewtransactionService(transactionPort)
	controller := controller.NewTransactionController(transactionService)

	fmt.Println(controller.Transacionar(950.00, 50.00))
}
