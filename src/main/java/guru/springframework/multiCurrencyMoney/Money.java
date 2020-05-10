package guru.springframework.multiCurrencyMoney;

public class Money implements Expression{
	
	protected int amount;
	protected String currency;
	
	public Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	public String currency() {
		return currency;
	}
	
	public static Money createDollar(int amount) {
		return new Money(amount, "USD");
	}
	
	public static Money createFranc(int amount) {
		return new Money(amount, "CHF");
	}
	
	public boolean equals(Object object) {
		
		Money money = (Money) object;
		return amount == money.amount 
				&& currency == money.currency;
		
	}
	
	@Override
	public Money reduce(Bank bank, String toCurrency) {
//		int rate = (currency.equals("CHF") && toCurrency.equals("USD")) ? 2 : 1;
//		return this;
		return new Money(amount / bank.rate(this.currency, toCurrency), toCurrency);
	}

	@Override
	public String toString() {
		return "Money{" +
				"amount=" + amount +
				", currency='" + currency + '\''+
				'}';
	}


	public Money times(int multiplier) {
		return new Money(amount*multiplier, this.currency);
	}
	
	public Expression plus(Money addend) {
		return new Sum(this, addend);
	}


}
