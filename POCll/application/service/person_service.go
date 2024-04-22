package service

import (
	"POCLL/application/domain"
	"POCLL/application/port/out"
	"fmt"
)

type PersonService struct {
	personPort out.PersonPort
}

func NewPersonService(personPort out.PersonPort) *PersonService {
	fmt.Println("Passou por Service Person application service")
	return &PersonService{personPort: personPort}
}

func (ps *PersonService) RegisterNewPerson(person domain.Person) string {
	registerNewPersonResponse := ps.personPort.RegisterNewPerson(person)
	return registerNewPersonResponse
}

func (ps *PersonService) DeletePerson(person domain.Person) string {
	deletedPerson := ps.personPort.DeletePerson(person)
	return deletedPerson
}
