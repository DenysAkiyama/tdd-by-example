package guru.springframework.multiCurrencyMoney;

public abstract class Money {
	
	protected int amount;
	
	public abstract Money times(int multiplier);
	
	public static Money createDollar(int amount) {
		return new Dollar(amount);
	}
	
	public static Money createFranc(int amount) {
		return new Franc(amount);
	}
	
	public boolean equals(Object object) {
		
		Money money = (Money) object;
		return amount == money.amount 
				&& getClass().equals(object.getClass());
		
	}
}
