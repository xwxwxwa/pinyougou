package com.pinyougou.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;
/**
 * 认证类
 * @author Administrator
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService {
	
	
	
	private SellerService sellerService;
	private TbSeller tbSeller;
	//通过这种方式注入  sellerservice  使用它里面的方法
	public void setSellerService(SellerService sellerService) {
		this.sellerService = sellerService;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("从认证类走了");
		//创建一个角色列表
		List<GrantedAuthority> grantAuths=new ArrayList();
		grantAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		//商家登录名就是id
		tbSeller = sellerService.findOne(username); 
		//只有状态码为1的商家才能登录
		if(tbSeller!=null)
		{
			if("1".equals(tbSeller.getStatus()))
			{
				//user角色创建需要那些参数    用户名  密码，和权限的集合，返回一个登陆对象
 				return new User(username,tbSeller.getPassword(),grantAuths);
			}
			else
			{
				return null;
			}
			
		}
		else
		{
			return null;
		}
		
		
	}

}
