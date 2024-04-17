package news_http

import (
	"fmt"
	"poc/adapter/output/model/response"
	"poc/application/domain"
	"poc/configuration/rest_err"
	"strconv"
	"time"

	resty "github.com/go-resty/resty/v2"
)

type newsClient struct{}

func NewNewsClient() *newsClient {
	client = resty.New().SetBaseURL("https://newsapi.org/v2")
	return &newsClient{}
}

var (
	client *resty.Client
)

func (nc *newsClient) GetNewsPort(newsDomain domain.NewsReqDomain) (*domain.NewsDomain, *rest_err.RestErr) {

	newsResponse := &response.NewsClientResponse{}

	_, err := client.R().
		SetQueryParams(map[string]string{
			"q":      newsDomain.Subject,
			"from":   newsDomain.From,
			"apiKey": "450b8b23f234403595331dee8050faa2",
			"order":  "asc",
			"random": strconv.FormatInt(time.Now().Unix(), 10),
		}).SetResult(&response.NewsClientResponse{}).
		Get("/everything")

	if err != nil {
		fmt.Println(err)
		return nil, rest_err.NewInternalServerError("Error trying to call NewsApi with Params")
	}

	fmt.Println(newsResponse)
	fmt.Println(*newsResponse)
	fmt.Println(&newsResponse)

	newResponseDomain := domain.ConvertNewsClientResponseToNewsDomain(newsResponse)

	return &newResponseDomain, nil
}
