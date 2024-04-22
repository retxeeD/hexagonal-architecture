package in

import (
	"POCLL/adapter/models"
	"POCLL/application/domain"
	"POCLL/application/port/in"
	"fmt"
	"strings"
)

type PersonController struct {
	personUseCase in.PersonUseCase
}

func NewPersonController(personUseCase in.PersonUseCase) *PersonController {
	fmt.Println("Passou por Controller adapter port in")
	return &PersonController{personUseCase: personUseCase}
}

func (pc PersonController) RegisterNewPerson(personDto models.PersonDto) string {
	name := strings.Split(personDto.Name, " ")
	personDomain := domain.Person{
		Name:    name[0],
		Surname: name[1],
	}
	return pc.personUseCase.RegisterNewPerson(personDomain)
}

func (pc PersonController) DeletePerson(personDto models.PersonDto) string {
	name := strings.Split(personDto.Name, " ")
	personDomain := domain.Person{
		Name:    name[0],
		Surname: name[1],
	}
	return pc.personUseCase.DeletePerson(personDomain)
}
