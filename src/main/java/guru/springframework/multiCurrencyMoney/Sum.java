package guru.springframework.multiCurrencyMoney;

public class Sum implements Expression {

	Money augmend;
	Money addmend;
	
	public Sum(Money augmend, Money addmend) {
		super();
		this.augmend = augmend;
		this.addmend = addmend;
	}
	
	@Override
	public Money reduce(Bank bank, String toCurrency) {
		int amount = augmend.amount + addmend.amount;
		return new Money(amount, toCurrency);
	}
}
