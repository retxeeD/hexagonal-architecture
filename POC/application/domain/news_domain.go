package domain

import (
	"poc/adapter/output/model"
	"poc/adapter/output/model/response"
)

type NewsReqDomain struct {
	Subject string
	From    string
}

type NewsDomain struct {
	Status       string
	TotalResults string
	Articles     []model.Article
}

func ConvertNewsClientResponseToNewsDomain(dto *response.NewsClientResponse) NewsDomain {
	return NewsDomain{
		Status:       dto.Status,
		TotalResults: dto.TotalResults,
		Articles:     dto.Articles,
	}
}
