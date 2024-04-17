package output

import (
	"poc/application/domain"
	"poc/configuration/rest_err"
)

type NewsPort interface {
	GetNewsPort(domain.NewsReqDomain) (*domain.NewsDomain, *(rest_err.RestErr))
}
