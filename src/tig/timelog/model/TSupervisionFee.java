/**
 * 
 */
package tig.timelog.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TSupervisionFee implements Serializable {
	private String profIntId;
	private BigDecimal paymentAmount;
	private Date paymentTime;
	private String summary;
	private String orgType;

	private Date paymentTimeStart;
	private Date paymentTimeEnd;

	/**
	 * @return the profIntId
	 */
	public String getProfIntId() {
		return profIntId;
	}

	/**
	 * @param profIntId
	 *            the profIntId to set
	 */
	public void setProfIntId(String profIntId) {
		this.profIntId = profIntId;
	}

	/**
	 * @return the paymentAmount
	 */
	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	/**
	 * @param paymentAmount
	 *            the paymentAmount to set
	 */
	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	/**
	 * @return the paymentTime
	 */
	public Date getPaymentTime() {
		return paymentTime;
	}

	/**
	 * @param paymentTime
	 *            the paymentTime to set
	 */
	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	/**
	 * @return the summary
	 */
	public String getSummary() {
		return summary;
	}

	/**
	 * @param summary
	 *            the summary to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * @return the orgType
	 */
	public String getOrgType() {
		return orgType;
	}

	/**
	 * @param orgType
	 *            the orgType to set
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/**
	 * @return the paymentTimeStart
	 */
	public Date getPaymentTimeStart() {
		return paymentTimeStart;
	}

	/**
	 * @param paymentTimeStart the paymentTimeStart to set
	 */
	public void setPaymentTimeStart(Date paymentTimeStart) {
		this.paymentTimeStart = paymentTimeStart;
	}

	/**
	 * @return the paymentTimeEnd
	 */
	public Date getPaymentTimeEnd() {
		return paymentTimeEnd;
	}

	/**
	 * @param paymentTimeEnd the paymentTimeEnd to set
	 */
	public void setPaymentTimeEnd(Date paymentTimeEnd) {
		this.paymentTimeEnd = paymentTimeEnd;
	}

}
