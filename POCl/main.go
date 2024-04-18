package main

import (
	"POCL/adapter/input/controller"
	"POCL/adapter/output"
	"POCL/application/service"
	"fmt"
)

func main() {
	fmt.Println("start hexagonal test")
	transactionPort := output.NewTransactionPix()
	transactionService := service.NewtransactionService(transactionPort)
	controllerPix := controller.NewTransactionControllerPix(transactionService)
	controllerBoleto := controller.NewTransactionControllerBoleto(transactionService)

	fmt.Println(controllerPix.TransacionarPix(950.00, 50.00))
	fmt.Println(controllerBoleto.TransacionarBoleto(100.00, 50.00))

}
