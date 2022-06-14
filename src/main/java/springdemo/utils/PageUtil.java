package springdemo.utils;
import springdemo.dao.BaseDao;
import springdemo.entity.PageData;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class PageUtil {

	/**
	 * 单表分页通用 泛型版
	 *
	 * @param entity
	 * @param dao
	 * @param <E>
	 * @return
	 */
	public static <E> PageData<E> getPageData(E entity, BaseDao<E> dao) {
		return commonPage(entity, dao, null);
	}

	/**
	 * 复杂查询分页通用 查询list的方法为methodName，总个数为{methodName}+Count
	 *
	 * @param entity
	 * @param dao
	 * @param <E>
	 * @return
	 */
	public static <E> PageData<E> getPageData(E entity, Object dao, String methodName) {
		return commonPage(entity, dao, methodName);
	}

	/**
	 * 单表分页通用 map版
	 *
	 * @param map
	 * @param dao
	 * @return
	 */
	public static Map<String, Object> getPageData(Map<String, Object> map, BaseDao<Map<String, Object>> dao) {
		return commonPage(map, dao, null);
	}

	/**
	 * 负责查询分页通用 查询list的方法为methodName，总个数为{methodName}+Count map版
	 *
	 * @param map
	 * @param dao
	 * @param methodName
	 * @return
	 */
	public static Map<String, Object> getPageData(Map<String, Object> map, Object dao, String methodName) {
		return commonPage(map, dao, methodName);
	}

	/**
	 * 公共分页 泛型版
	 *
	 * @param entity
	 * @param dao
	 * @param methodName
	 * @param <E>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static <E> PageData<E> commonPage(E entity, Object dao, String methodName) {
		List<String> orderData;
		Integer currentPage;
		Integer pageSize;
		Class<?> entityClass = entity.getClass();
		try {
			Field orderDataFiled = entityClass.getSuperclass().getDeclaredField("orderData");
			orderDataFiled.setAccessible(true);
			orderData = CastUtil.cast(orderDataFiled.get(entity));

			Field currentPageFiled = entityClass.getSuperclass().getDeclaredField("currentPage");
			currentPageFiled.setAccessible(true);
			currentPage = CastUtil.cast(currentPageFiled.get(entity));

			Field pageSizeFiled = entityClass.getSuperclass().getDeclaredField("pageSize");
			pageSizeFiled.setAccessible(true);
			pageSize = CastUtil.cast(pageSizeFiled.get(entity));
			//默认每页十条
			pageSize = pageSize == null ? 10 : pageSize;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		StringBuilder orderStr = getOrderStr(orderData);
		Integer start = (currentPage - 1) * pageSize;
		// 分页开始 页码（oracle用）
		Integer startIndex = (currentPage - 1) * pageSize;
		// 分页结束页码（oracle用）
		Integer endIndex = currentPage * pageSize;
		int totalPage = 1;
		try {
            initEntity(entity, entityClass, orderStr.toString(), start, pageSize, startIndex, endIndex);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		int totalCount;
		List<E> resultList;
		try {
			if (methodName == null) {
				totalCount = ((BaseDao<E>) dao).likeSelectCount(entity).intValue();
				resultList = ((BaseDao<E>) dao).likeSelect(entity);
			} else {
				// 获取总个数
				Method getCountMethod = dao.getClass().getMethod(methodName + "Count", entity.getClass());
				totalCount = ((Long) getCountMethod.invoke(dao, entity)).intValue();
				Method getResultListMethod = dao.getClass().getMethod(methodName, entity.getClass());
				resultList = CastUtil.cast(getResultListMethod.invoke(dao, entity));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		if (totalCount != 0) {
			if (totalCount % pageSize == 0) {
				totalPage = totalCount / pageSize;
			} else {
				totalPage = totalCount / pageSize + 1;
			}
		}
		PageData<E> pageData = new PageData<>();
		// 当前页
		pageData.setCurrentPage(currentPage);
		pageData.setCount(totalCount);
		pageData.setTotalPage(totalPage);
		pageData.setResult(resultList);
		return pageData;
	}

	/**
	 * 公共分页 map版
	 *
	 * @param map
	 * @param dao
	 * @param methodName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, Object> commonPage(Map<String, Object> map, Object dao, String methodName) {
		List<String> orderData = CastUtil.cast(map.get("orderData"));
		StringBuilder orderStr = getOrderStr(orderData);
		Integer currentPage = (Integer) map.get("currentPage");
		Integer pageSize = (Integer) map.get("pageSize");
		pageSize = pageSize == null ? 10 : pageSize;
		Integer start = (currentPage - 1) * pageSize;
		// 分页开始 页码
		Integer startIndex = (currentPage - 1) * pageSize;
		// 分页结束页码
		Integer endIndex = currentPage * pageSize;
        int totalPage = 1;
		// sql中的start
		map.put("start", start);
		// 每页显示pageSize条
		map.put("pageSize", pageSize);
		// sql中的startIndex（oracle用）
		map.put("startIndex", startIndex);
		// sql中的endIndex（oracle用）
		map.put("endIndex", endIndex);
		//排序条件
		map.put("orderStr", orderStr.toString());
		int totalCount;
		List<Map<String, Object>> resultList;
		try {
			if (methodName == null) {
				totalCount = ((BaseDao) dao).likeSelectCount(map).intValue();
				resultList = ((BaseDao) dao).likeSelect(map);
			} else {
				// 获取总个数
				Method getCountMethod = dao.getClass().getMethod(methodName + "Count", map.getClass());
				totalCount = ((Long) getCountMethod.invoke(dao, map)).intValue();
				Method getResultListMethod = dao.getClass().getMethod(methodName, map.getClass());
				resultList = CastUtil.cast(getResultListMethod.invoke(dao, map));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		if (totalCount != 0) {
			if (totalCount % pageSize == 0) {
				totalPage = totalCount / pageSize;
			} else {
				totalPage = totalCount / pageSize + 1;
			}
		}
		map.clear();
		// 当前页
		map.put("currentPage", currentPage);
		map.put("count", totalCount);
		map.put("totalPage", totalPage);
		map.put("result", resultList);
		return map;
	}

	private static StringBuilder getOrderStr(List<String> orderData) {
		StringBuilder orderStr = new StringBuilder();
		if (orderData != null) {
			for (int i = 0; i < orderData.size(); i++) {
				if (i == orderData.size() - 1) {
					orderStr.append(orderData.get(i));
					break;
				}
				orderStr.append(orderData.get(i)).append(",");
			}
		}
		return orderStr;
	}

	private static <E> void initEntity(E entity, Class<?> entityClass, String orderStr, Integer start, Integer pageSize, Integer startIndex, Integer endIndex) throws NoSuchFieldException, IllegalAccessException {
        Field startFiled = entityClass.getSuperclass().getDeclaredField("start");
        startFiled.setAccessible(true);
        // sql中的start
        startFiled.set(entity,start);
        Field pageSizeFiled = entityClass.getSuperclass().getDeclaredField("pageSize");
        pageSizeFiled.setAccessible(true);
        // 每页显示的条数
        pageSizeFiled.set(entity, pageSize);
        //oracle使用
        Field startIndexFiled = entityClass.getSuperclass().getDeclaredField("startIndex");
        startIndexFiled.setAccessible(true);
        startIndexFiled.set(entity,startIndex);
        Field endIndexFiled = entityClass.getSuperclass().getDeclaredField("endIndex");
        endIndexFiled.setAccessible(true);
        endIndexFiled.set(entity,endIndex);
        Field orderStrFiled = entityClass.getSuperclass().getDeclaredField("orderStr");
        orderStrFiled.setAccessible(true);
        //排序条件
        orderStrFiled.set(entity, orderStr);
    }

}

