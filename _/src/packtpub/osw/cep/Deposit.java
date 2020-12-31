package packtpub.osw.cep;

/**
 * Sample event class for CEP.
 */
public class Deposit {
	private float amount;
	private String customerId;

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the amount
	 */
	public float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public String toString(){
		return "customer:" + customerId + "-amount:" + amount;
	}
}
