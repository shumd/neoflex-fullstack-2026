import { useState } from 'react'
import axios from 'axios'
import './App.css'

interface CalculationRequest {
    amount: number
    months: number
    rate: number
}

interface CalculationResponse {
    total: number
    profit: number
    initialAmount: number
}

function App() {
    const [amount, setAmount] = useState('')
    const [months, setMonths] = useState('')
    const [rate, setRate] = useState('')
    const [result, setResult] = useState<CalculationResponse | null>(null)

    const handleSubmit = async (event: React.SubmitEvent) => {
        event.preventDefault()

        if (
            Number(amount) <= 0 ||
            Number(months) <= 0 ||
            Number(rate) <= 0
        ) {
            alert('Все значения должны быть больше 0')
            return
        }

        const request: CalculationRequest = {
            amount: Number(amount),
            months: Number(months),
            rate: Number(rate)
        }

        try {
            const response = await axios.post<{
                total: number
                profit: number
            }>(
                'http://localhost:8080/api/calculate',
                request
            )

            setResult({
                initialAmount: request.amount,
                total: response.data.total,
                profit: response.data.profit
            })
        } catch (error) {
            console.error('Ошибка при расчёте:', error)
        }
    }

    return (
        <div className="app">
            <div className="calculator">
                <h1>Калькулятор вклада</h1>

                <form onSubmit={handleSubmit} className="calculator-form">
                    <div className="form-group">
                        <label htmlFor="amount">Сумма вклада</label>
                        <input
                            id="amount"
                            type="number"
                            placeholder="100 000"
                            value={amount}
                            onChange={(event) => setAmount(event.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="months">Срок (месяцы)</label>
                        <input
                            id="months"
                            type="number"
                            placeholder="12"
                            value={months}
                            onChange={(event) => setMonths(event.target.value)}
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="rate">Годовая ставка (%)</label>
                        <input
                            id="rate"
                            type="number"
                            step="0.01"
                            placeholder="8.5"
                            value={rate}
                            onChange={(event) => setRate(event.target.value)}
                        />
                    </div>

                    <button type="submit">
                        Рассчитать
                    </button>
                </form>

                {result && (
                    <div className="result">
                        <h2>Результат расчёта</h2>

                        <div className="result-row">
                            <span>Начальная сумма</span>
                            <strong>{result.initialAmount} ₽</strong>
                        </div>

                        <div className="result-row">
                            <span>Итоговая сумма</span>
                            <strong>{result.total} ₽</strong>
                        </div>

                        <div className="result-row profit">
                            <span>Доход</span>
                            <strong>{result.profit} ₽</strong>
                        </div>
                    </div>
                )}
            </div>
        </div>
    )
}

export default App