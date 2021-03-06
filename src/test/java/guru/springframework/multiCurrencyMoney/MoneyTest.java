package guru.springframework.multiCurrencyMoney;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class MoneyTest {

	@Test
	void testMultiplication() {
		Money five = Money.createDollar(5);
		assertEquals(Money.createDollar(10), five.times(2));
		assertEquals(Money.createDollar(15), five.times(3));
		
		Money fiveF = Money.createFranc(5);
		assertEquals(Money.createFranc(10), fiveF.times(2));
		assertEquals(Money.createFranc(15), fiveF.times(3));
		
	}
	@Test
	void testEquality() {
		assertEquals(Money.createDollar(5),Money.createDollar(5));
		assertNotEquals(Money.createDollar(5),Money.createDollar(8));
		
		assertEquals(Money.createFranc(5), Money.createFranc(5));
		assertNotEquals(Money.createFranc(5), Money.createDollar(8));
		
		assertNotEquals(Money.createDollar(5), Money.createFranc(5));
	}
	
	@Test
	void testCurrency() {
		assertEquals("USD", Money.createDollar(1).currency());
		assertEquals("CHF", Money.createFranc(1).currency());
		
	}
	
	@Test
	void testSimpleAddition() {
		Money five = Money.createDollar(5);
		Expression sum = five.plus(five);
		Bank bank = new Bank();
		Expression reduced = bank.reduce(sum, "USD");
		assertEquals(Money.createDollar(10), reduced);
	}
	
	@Test
	void testPlusReturnsSum() {
		Money five = Money.createDollar(5);
		Expression result = five.plus(five);
		Sum sum = (Sum) result;
		
		assertEquals(five, sum.augmend);
		assertEquals(five, sum.addmend);
	}
	
	@Test
	void testReduceSum() {
		Expression sum = new Sum(Money.createDollar(3), Money.createDollar(4));
		Bank bank = new Bank();
		Money result = bank.reduce(sum, "USD");
		assertEquals(Money.createDollar(7), result);
	}
	
	@Test
	void testReduceMoney() {
		Bank bank = new Bank();
		Expression result = bank.reduce(Money.createDollar(1), "USD");
		
		assertEquals(Money.createDollar(1), result);
	}
	
	@Test
	void testReduceMoneyDifferentCurrency() {
		Bank bank = new Bank();
		bank.addRate("CHF","USD", 2);
		Money result = bank.reduce(Money.createFranc(2), "USD");
		assertEquals(Money.createDollar(1), result);
	}
	
	@Test
	public void testSumPlusMoney() {
		Expression fiveBucks = Money.createDollar(5);
		Expression tenFrancs = Money.createFranc(10);
		
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Expression sum = new Sum(fiveBucks, tenFrancs).plus(fiveBucks);
		Money result = bank.reduce(sum, "USD");
		
		assertEquals(Money.createDollar(15), result);
	}
	
	@Test
	public void testSumTimes() {
		Expression fiveBucks = Money.createDollar(5);
		Expression tenFrancs = Money.createFranc(10);
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Expression sum = new Sum(fiveBucks, tenFrancs).times(2);
		Money result = bank.reduce(sum, "USD");
		
		assertEquals(Money.createDollar(20), result);
	}
	
	@Test
	void testIdentityRate() {
		assertEquals(1, new Bank().rate("USD", "USD"));
		assertEquals(1, new Bank().rate("CHF", "CHF"));
	}
	
	@Test
	public void testMixedAddition() {
		Expression fiveBucks = Money.createDollar(5);
		Expression tenFrancs = Money.createFranc(10);
		
		Bank bank = new Bank();
		bank.addRate("CHF", "USD", 2);
		Money result = bank.reduce(fiveBucks.plus(tenFrancs), "USD");
		
		assertEquals(Money.createDollar(10), result);
	}
}
