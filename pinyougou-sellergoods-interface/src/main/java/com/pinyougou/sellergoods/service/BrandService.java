package com.pinyougou.sellergoods.service;

import java.util.List;

import com.pinyougou.pojo.TbBrand;

import entity.PageResult;

/**
 * 品牌接口
 * @author Administrator
 *这个接口是品牌的接口  里面 导入了页面的实体类  商品的pojo
 */
public interface BrandService {

	public List<TbBrand> findAll();
	
	/**
	 * 品牌分页
	 * @param pageNum 当前页面
	 * @param pageSize 每页记录数
	 * @return
	 * 分页  当前第几页和大小
	 */
	public PageResult findPage(int pageNum,int pageSize);
	
	/**
	 * 增加 
	 * @param brand
	 */
	public void add(TbBrand brand);
	
	/**
	 * 根据ID查询实体
	 * 就是如果要进行修改的话  就要先把数据实体查出来展示数据
	 * 在进行修改
	 * @param id
	 * @return
	 */
	public TbBrand findOne(Long id);
	
	/**
	 * 修改（这个函数就是进行修改的方法）
	 * @param brand
	 */
	public void update(TbBrand brand);
	
	/**
	 * 删除
	 * @param ids
	 */
	public void delete(Long[] ids);
	
	
	/**
	 * 品牌分页
	 * @param pageNum 当前页面
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbBrand brand, int pageNum,int pageSize);
	
}
