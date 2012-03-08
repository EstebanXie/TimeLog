/**
 * 
 */
package tig.timelog.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Zero.Zhang
 * 
 */
public class TShareHolderInfo implements Serializable {
	private String shareHolderId;
	private String proIntId;
	private String shareHolderName;
	private BigDecimal shareAmount;
	private BigDecimal shareRate;

	/**
	 * @return the shareHolderId
	 */
	public String getShareHolderId() {
		return shareHolderId;
	}

	/**
	 * @param shareHolderId
	 *            the shareHolderId to set
	 */
	public void setShareHolderId(String shareHolderId) {
		this.shareHolderId = shareHolderId;
	}

	/**
	 * @return the proIntId
	 */
	public String getProIntId() {
		return proIntId;
	}

	/**
	 * @param proIntId
	 *            the proIntId to set
	 */
	public void setProIntId(String proIntId) {
		this.proIntId = proIntId;
	}

	/**
	 * @return the shareHolderName
	 */
	public String getShareHolderName() {
		return shareHolderName;
	}

	/**
	 * @param shareHolderName
	 *            the shareHolderName to set
	 */
	public void setShareHolderName(String shareHolderName) {
		this.shareHolderName = shareHolderName;
	}

	/**
	 * @return the shareAmount
	 */
	public BigDecimal getShareAmount() {
		return shareAmount;
	}

	/**
	 * @param shareAmount
	 *            the shareAmount to set
	 */
	public void setShareAmount(BigDecimal shareAmount) {
		this.shareAmount = shareAmount;
	}

	/**
	 * @return the shareRate
	 */
	public BigDecimal getShareRate() {
		return shareRate;
	}

	/**
	 * @param shareRate
	 *            the shareRate to set
	 */
	public void setShareRate(BigDecimal shareRate) {
		this.shareRate = shareRate;
	}

}
