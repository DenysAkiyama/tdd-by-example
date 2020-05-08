package guru.springframework.multiCurrencyMoney;

public class Money {
	
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
		return new Dollar(amount, "USD");
	}
	
	public static Money createFranc(int amount) {
		return new Franc(amount, "CHF");
	}
	
	public boolean equals(Object object) {
		
		Money money = (Money) object;
		return amount == money.amount 
				&& currency == money.currency;
		
	}

	public Money times(int multiplier) {
		return new Money(amount*multiplier, this.currency);
	}
}
