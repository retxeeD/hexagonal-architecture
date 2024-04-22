package out

import (
	"POCLL/application/domain"
)

type PersonPort interface {
	RegisterNewPerson(person domain.Person) string
	DeletePerson(person domain.Person) string
}
