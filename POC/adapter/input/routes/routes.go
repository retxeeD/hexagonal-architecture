package routes

import (
	"poc/adapter/input/controller"
	"poc/adapter/output/news_http"
	"poc/application/service"

	"github.com/gin-gonic/gin"
)

func InitRoutes(r *gin.Engine) {
	newsClient := news_http.NewNewsClient()
	newsService := service.NewNewsService(newsClient)
	controller := controller.NewNewsController(newsService)

	r.GET("/news", controller.GetNews)
}
