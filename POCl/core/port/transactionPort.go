package port

type TransactionPort interface {
	Transacionar(valor float64) bool
}
