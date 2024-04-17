package request

import "time"

type NewsRequest struct {
	Subject string    `form:"subject" binding:"required,min=2"`
	From    time.Time `from:"from" biding:"required" time_format:"2006-01-02"`
}
