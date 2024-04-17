package response

import (
	"poc/adapter/output/model"
)

/**type Article struct {
	Source      string
	ID          string
	Name        string
	Author      string
	Title       string
	Description string
	URL         string
	UrlToImage  string
	PublishedAt string
	Content     string
}**/

type NewsClientResponse struct {
	Status       string
	TotalResults string
	Articles     []model.Article
}
