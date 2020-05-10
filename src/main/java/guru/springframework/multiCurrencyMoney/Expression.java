package guru.springframework.multiCurrencyMoney;

public interface Expression {

	Money reduce(Bank bank, String toCurrency);

}
