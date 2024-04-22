package out

import (
	"POCLL/application/domain"
	"fmt"
)

type OperationsPerson struct{}

func NewOperationsPerson() *OperationsPerson {
	fmt.Println("Passou por Operations Person adapter port out")
	return &OperationsPerson{}
}

func (op *OperationsPerson) RegisterNewPerson(person domain.Person) string {
	return ("Person " + person.Name + " " + person.Surname + " was saved.")
}

func (op *OperationsPerson) DeletePerson(person domain.Person) string {
	return ("Person " + person.Name + " " + person.Surname + " was deleted.")
}
