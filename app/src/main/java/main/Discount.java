package main;

public class Discount {
	private Identity identity;
	private int hour;
	private int min;
	private double discountPrice = 0;

	public Discount(Identity identity, String dateTime) throws IllegalArgumentException {

		this.identity = identity;
		this.hour = InputNormalization.extractHour(dateTime);
		this.min = InputNormalization.extractMin(dateTime);

		checkException();
	}

	public void checkException() throws IllegalArgumentException {
		if (3 > identity.getAge()) {
			throw new IllegalArgumentException("Your age is too young.");
		} else if (identity.getAge() > 75) {
			throw new IllegalArgumentException("Your age doesn't meet the requirements.");
		} else if ((5 > hour || hour > 22) || (hour == 22 && min > 0)) {
			throw new IllegalArgumentException("Business hours: 05:00-22:00");
		} else {
			queryDiscount(identity, hour);
		}
	}

	private void queryDiscount(Identity identity, int hour) {
		if (identity.isMember()) {
			discountPrice = 0.5;
		} else if (identity.isGroup()) {
			discountPrice = 0.7;
		} else if (12 > identity.getAge() || identity.getAge() >= 60) {
			discountPrice = 0.8;
		} else if (5 <= hour && hour < 7) {
			discountPrice = 0.8;
		} else {
			discountPrice = 1;
		}
	}

	public double getDiscount() {
		return discountPrice;
	}
}
