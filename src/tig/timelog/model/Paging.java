/**
 * 
 */
package tig.timelog.model;

import java.io.Serializable;

/**
 * @author Zero.Zhang
 * 
 */
public class Paging implements Serializable {
	private Integer pageNum = 1;
	private Integer numOfPages; //页数
	private Integer numOfRecords;//记录数
	private Integer recordsPerPage =7;
	private String sort;
	private String sortDir;
	/**
	 * @return the pageNum
	 */
	public Integer getPageNum() {
		return pageNum;
	}
	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	/**
	 * @return the numOfPages
	 */
	public Integer getNumOfPages() {
		return numOfPages;
	}
	/**
	 * @param numOfPages the numOfPages to set
	 */
	public void setNumOfPages(Integer numOfPages) {
		this.numOfPages = numOfPages;
	}
	/**
	 * @return the numOfRecords
	 */
	public Integer getNumOfRecords() {
		return numOfRecords;
	}
	/**
	 * @param numOfRecords the numOfRecords to set
	 */
	public void setNumOfRecords(Integer numOfRecords) {
		this.numOfRecords = numOfRecords;
	}
	/**
	 * @return the recordsPerPage
	 */
	public Integer getRecordsPerPage() {
		return recordsPerPage;
	}
	/**
	 * @param recordsPerPage the recordsPerPage to set
	 */
	public void setRecordsPerPage(Integer recordsPerPage) {
		this.recordsPerPage = recordsPerPage;
	}
	/**
	 * @return the sort
	 */
	public String getSort() {
		return sort;
	}
	/**
	 * @param sort the sort to set
	 */
	public void setSort(String sort) {
		this.sort = sort;
	}
	/**
	 * @return the sortDir
	 */
	public String getSortDir() {
		return sortDir;
	}
	/**
	 * @param sortDir the sortDir to set
	 */
	public void setSortDir(String sortDir) {
		this.sortDir = sortDir;
	}
}
