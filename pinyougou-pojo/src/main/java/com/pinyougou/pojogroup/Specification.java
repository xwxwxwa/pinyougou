package com.pinyougou.pojogroup;

import java.io.Serializable;
//这个pojo里面是一个包含  子选项的包装类  用来页面之间数据传输的载体
import java.util.List;

import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;
public class Specification implements Serializable{
/*	private int id;
	private char spec_name;  这是自己的错误语句  既然要包装  就直接包装进去就好了
	何必自己多此一举来写属性
	这是组合实体类
*/
	private TbSpecification specification;
	
	private List<TbSpecificationOption> specificationOptionList;

	public TbSpecification getSpecification() {
		return specification;
	}

	public void setSpecification(TbSpecification specification) {
		this.specification = specification;
	}

	public List<TbSpecificationOption> getSpecificationOptionList() {
		return specificationOptionList;
	}

	public void setSpecificationOptionList(List<TbSpecificationOption> specificationOptionList) {
		this.specificationOptionList = specificationOptionList;
	}

}
