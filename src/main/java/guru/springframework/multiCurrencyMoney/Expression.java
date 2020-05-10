package guru.springframework.multiCurrencyMoney;

public interface Expression {

	Money reduce(Bank bank, String toCurrency);

	public Expression plus(Expression addend);
	
	Expression times(int multiplier);
}
