package guru.springframework.multiCurrencyMoney;

public class Bank {

	/**
	 * 
	 * @param source: Moeda a ser convertida
	 * @param toCurrency: Moeda convertida
	 * @return
	 */
	Money reduce(Expression source, String toCurrency) {
		return Money.createDollar(10);
	}
}
