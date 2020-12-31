package packtpub.osw;

/**
 * Money Loan Request
 */
public class LoanRequest {
	private float amount;

	private String customerName;

	private boolean married;

	private float annualEarnings;

	private boolean creditHistory;

	private boolean previousCreditPaid;

	private boolean previousLegalSituation;

	/**
	 * Constructor.
	 * @param amount
	 * @param customerName
	 * @param married
	 * @param annualEarnings
	 * @param creditHistory
	 * @param previousCreditPaid
	 * @param previousLegalSituation
	 */
	public LoanRequest(float amount, String customerName, boolean married,
			float annualEarnings, boolean creditHistory,
			boolean previousCreditPaid, boolean previousLegalSituation) {
		super();
		this.amount = amount;
		this.customerName = customerName;
		this.married = married;
		this.annualEarnings = annualEarnings;
		this.creditHistory = creditHistory;
		this.previousCreditPaid = previousCreditPaid;
		this.previousLegalSituation = previousLegalSituation;
	}

	/**
	 * @return the amount
	 */
	public final float getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public final void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * @return the annualEarnings
	 */
	public final float getAnnualEarnings() {
		return annualEarnings;
	}

	/**
	 * @param annualEarnings the annualEarnings to set
	 */
	public final void setAnnualEarnings(float annualEarnings) {
		this.annualEarnings = annualEarnings;
	}

	/**
	 * @return the creditHistory
	 */
	public final boolean hasCreditHistory() {
		return creditHistory;
	}

	/**
	 * @param creditHistory the creditHistory to set
	 */
	public final void setCreditHistory(boolean creditHistory) {
		this.creditHistory = creditHistory;
	}

	/**
	 * @return the customerName
	 */
	public final String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public final void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the married
	 */
	public final boolean isMarried() {
		return married;
	}

	/**
	 * @param married the married to set
	 */
	public final void setMarried(boolean married) {
		this.married = married;
	}

	/**
	 * @return the previousCreditPaid
	 */
	public final boolean isPreviousCreditPaid() {
		return previousCreditPaid;
	}

	/**
	 * @param previousCreditPaid the previousCreditPaid to set
	 */
	public final void setPreviousCreditPaid(boolean previousCreditPaid) {
		this.previousCreditPaid = previousCreditPaid;
	}

	/**
	 * @return the previousLegalSituation
	 */
	public final boolean isPreviousLegalSituation() {
		return previousLegalSituation;
	}

	/**
	 * @param previousLegalSituation the previousLegalSituation to set
	 */
	public final void setPreviousLegalSituation(boolean previousLegalSituation) {
		this.previousLegalSituation = previousLegalSituation;
	}

	public String toString() {
		return "Loan for:" + customerName;
	}
}
