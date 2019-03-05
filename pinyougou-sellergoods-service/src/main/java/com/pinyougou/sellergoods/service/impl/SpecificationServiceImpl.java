package com.pinyougou.sellergoods.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pinyougou.mapper.TbSpecificationMapper;
import com.pinyougou.mapper.TbSpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationExample;
import com.pinyougou.pojo.TbSpecificationExample.Criteria;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.TbSpecificationOptionExample;
import com.pinyougou.pojogroup.Specification;
import com.pinyougou.sellergoods.service.SpecificationService;

import entity.PageResult;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		//specificationMapper.insert(specification);	现在改了页面传过来的实体之后dao的保存也要改	
		specificationMapper.insert(specification.getSpecification());//插入规格,返回id
		
		//插入详细规格
		for(TbSpecificationOption option:specification.getSpecificationOptionList()) {
			option.setSpecId(specification.getSpecification().getId());//设置这个详细 的规格属于哪个ID
			specificationOptionMapper.insert(option);
		}
		
	}

	
	/**
	 * 修改
	 * 修改的话  因为原来的规格详细里面的数据为list的  
	 * 可以先删除原来的数据，再重新插入
	 */
	@Override
	public void update(Specification specification){
		//更改规格数据1.先修改规格名称
		TbSpecification tbSpecification = specification.getSpecification();
		specificationMapper.updateByPrimaryKey(tbSpecification);
		//删除规格详细数据
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria=example.createCriteria();
		criteria.andSpecIdEqualTo(tbSpecification.getId());
		specificationOptionMapper.deleteByExample(example);
		//重新添加详细信息数据
		for(TbSpecificationOption option:specification.getSpecificationOptionList()){
			option.setSpecId(tbSpecification.getId());
			specificationOptionMapper.insert(option);
			
		}
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		//1获取规格
		TbSpecification tbSpecification= specificationMapper.selectByPrimaryKey(id);
		//2获取详细规格,因为获得详细信息是一个列表  需要创建查询条件
		TbSpecificationOptionExample example=new TbSpecificationOptionExample();
		//创建查询条件
		com.pinyougou.pojo.TbSpecificationOptionExample.Criteria criteria= example.createCriteria();
		//增加查询条件
		criteria.andSpecIdEqualTo(id);//根据规格ID查询		
		List<TbSpecificationOption> optionList = specificationOptionMapper.selectByExample(example);
		//3，构建返回结果。进行填充
		Specification spec=new Specification();
		spec.setSpecification(tbSpecification);
		spec.setSpecificationOptionList(optionList);
		return spec;

	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			specificationMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
