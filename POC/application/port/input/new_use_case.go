package input

import (
	"poc/application/domain"
	"poc/configuration/rest_err"
)

type NewsUseCase interface {
	GetNewsService(domain.NewsReqDomain) (*domain.NewsDomain, *rest_err.RestErr)
}
