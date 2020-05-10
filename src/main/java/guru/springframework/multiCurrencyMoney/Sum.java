package guru.springframework.multiCurrencyMoney;

public class Sum implements Expression {

	Expression augmend;
	Expression addmend;
	
	public Sum(Expression augmend, Expression addmend) {
		super();
		this.augmend = augmend;
		this.addmend = addmend;
	}
	
	@Override
	public Money reduce(Bank bank, String toCurrency) {
		int amount = augmend.reduce(bank,toCurrency).amount + 
				addmend.reduce(bank, toCurrency).amount;
		return new Money(amount, toCurrency);
	}

	@Override
	public Expression plus(Expression addend) {
		// TODO Auto-generated method stub
		return null;
	}
}
