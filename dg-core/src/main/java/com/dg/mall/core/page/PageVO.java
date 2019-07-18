package com.dg.mall.core.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.dg.mall.core.util.StringUtil;


import java.util.Collections;
import java.util.List;

/**
 * 封装提供swagger注解
 * 
 * @author admin
 *
 * @param <T>
 */
public class PageVO<T> implements IPage<T> {

	private static final long serialVersionUID = 8545996863226528798L;

	/**
	 * 查询数据列表
	 */
	private List<T> records = Collections.emptyList();

	/**
	 * 总数
	 */
	private long total = 0;

	/**
	 * 每页显示条数，默认 10
	 */
	private long size = 10;

	/**
	 * 当前页
	 */
	private long current = 1;

	/**
	 * <p>
	 * SQL 排序 ASC 数组
	 * </p>
	 */
	private String[] ascs;

	/**
	 * <p>
	 * SQL 排序 DESC 数组
	 * </p>
	 */
	private String[] descs;

	/**
	 * <p>
	 * 自动优化 COUNT SQL
	 * </p>
	 */
	private boolean optimizeCountSql = true;

	/**
	 * <p>
	 * 是否进行 count 查询
	 * </p>
	 */
	private boolean isSearchCount = true;

	public PageVO() {
		// to do nothing
	}

	/**
	 * <p>
	 * 分页构造函数
	 * </p>
	 *
	 * @param current 当前页
	 * @param size    每页显示条数
	 */
	public PageVO(long current, long size) {
		this(current, size, 0);
	}

	public PageVO(long current, long size, long total) {
		this(current, size, total, true);
	}

	public PageVO(long current, long size, boolean isSearchCount) {
		this(current, size, 0, isSearchCount);
	}

	public PageVO(long current, long size, long total, boolean isSearchCount) {
		if (current > 1) {
			this.current = current;
		}
		this.size = size;
		this.total = total;
		this.isSearchCount = isSearchCount;
	}

	public PageVO(long current, long size, long total, List<T> records) {
		if (current > 1) {
			this.current = current;
		}
		this.size = size;
		this.total = total;
		this.records = records;
	}

	public PageVO(IPage<?> ipage, List<T> records) {
		this.current = ipage.getCurrent();
		this.size = ipage.getSize();
		this.total = ipage.getTotal();
		this.records = records;
	}

	/**
	 * <p>
	 * 是否存在上一页
	 * </p>
	 *
	 * @return true / false
	 */
	public boolean hasPrevious() {
		return this.current > 1;
	}

	/**
	 * <p>
	 * 是否存在下一页
	 * </p>
	 *
	 * @return true / false
	 */
	public boolean hasNext() {
		return this.current < this.getPages();
	}

	@Override
	public List<T> getRecords() {
		return this.records;
	}

	@Override
	public PageVO<T> setRecords(List<T> records) {
		this.records = records;
		return this;
	}

	@Override
	public long getTotal() {
		return this.total;
	}

	@Override
	public PageVO<T> setTotal(long total) {
		this.total = total;
		return this;
	}

	@Override
	public long getSize() {
		return this.size;
	}

	@Override
	public PageVO<T> setSize(long size) {
		this.size = size;
		return this;
	}

	@Override
	public long getCurrent() {
		return this.current;
	}

	@Override
	public PageVO<T> setCurrent(long current) {
		this.current = current;
		return this;
	}

	@Override
	public String[] ascs() {
		return ascs;
	}

	@Override
	public List<OrderItem> orders() {
		return null;
	}

	public PageVO<T> setAscs(List<String> ascs) {
		if (CollectionUtils.isNotEmpty(ascs)) {
			this.ascs = StringUtil.camelToUnderline(ascs.toArray(new String[0]));
		}
		return this;
	}

	/**
	 * <p>
	 * 升序
	 * </p>
	 *
	 * @param ascs 多个升序字段
	 */
	public PageVO<T> setAsc(String... ascs) {
		this.ascs = StringUtil.camelToUnderline(ascs);
		return this;
	}

	@Override
	public String[] descs() {
		return descs;
	}

	public PageVO<T> setDescs(List<String> descs) {
		if (CollectionUtils.isNotEmpty(descs)) {
			this.descs = StringUtil.camelToUnderline(descs.toArray(new String[0]));
		}
		return this;
	}

	/**
	 * <p>
	 * 降序
	 * </p>
	 *
	 * @param descs 多个降序字段
	 */
	public PageVO<T> setDesc(String... descs) {
		this.descs = StringUtil.camelToUnderline(descs);
		return this;
	}

	@Override
	public boolean optimizeCountSql() {
		return optimizeCountSql;
	}

	@Override
	public boolean isSearchCount() {
		if (total < 0) {
			return false;
		}
		return isSearchCount;
	}

	public PageVO<T> setSearchCount(boolean isSearchCount) {
		this.isSearchCount = isSearchCount;
		return this;
	}

	public PageVO<T> setOptimizeCountSql(boolean optimizeCountSql) {
		this.optimizeCountSql = optimizeCountSql;
		return this;
	}
}
