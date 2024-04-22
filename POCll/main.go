package main

import (
	"POCLL/adapter/models"
	"POCLL/adapter/port/in"
	"POCLL/adapter/port/out"
	"POCLL/application/service"
	"fmt"
)

func main() {
	fmt.Println("start hexagonal test")
	personOperations := out.NewOperationsPerson()
	personService := service.NewPersonService(personOperations)
	controllerPerson := in.NewPersonController(personService)

	personDto := models.PersonDto{
		Name: "Pedro Lima",
	}

	fmt.Println(controllerPerson.RegisterNewPerson(personDto))
	fmt.Println(controllerPerson.DeletePerson(personDto))
}
