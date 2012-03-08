package tig.timelog.util;

import java.util.Map;

public final class DataStoreLocal {
	@SuppressWarnings("unchecked")
	private static final ThreadLocal data = new ThreadLocal();

	private DataStoreLocal() {
	}

	@SuppressWarnings("unchecked")
	public static void addData(Map map) {
		data.set(map);
	}

	@SuppressWarnings("unchecked")
	public static Map getData() {
		Object obj = data.get();
		return obj == null ? null : (Map) obj;
	}
}
