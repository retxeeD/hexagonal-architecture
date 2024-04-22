package in

import "POCLL/application/domain"

type PersonUseCase interface {
	RegisterNewPerson(person domain.Person) string
	DeletePerson(person domain.Person) string
}
