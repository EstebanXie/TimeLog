/**
 * 
 */
package tig.timelog.util;

import tig.timelog.model.Paging;

/**
 * @author Zero.Zhang
 * 
 */
public class PagingUtil {
	public static void getPaging(Paging paging, int listSize) {
		paging.setNumOfRecords(listSize);
		if (paging.getNumOfRecords() > paging.getRecordsPerPage()) {
			paging.setNumOfPages((int) java.lang.Math.ceil((double) paging
					.getNumOfRecords()
					/ (double) paging.getRecordsPerPage()));
		} else {
			paging.setNumOfPages(1);
		}
	}

	public static int getStartLimit(Paging paging) {
		int iStartLimit = 0;
		if (paging.getPageNum() == 1) {
			iStartLimit = 0;
		} else {
			iStartLimit = (paging.getPageNum() - 1)
					* paging.getRecordsPerPage();
		}

		return iStartLimit;
	}

	public static int getLastLimit(int iStartLimit, Paging paging, int size) {
		int end = iStartLimit + paging.getRecordsPerPage();
		if (end > size) {
			end = size;
		}

		return end;
	}
}
