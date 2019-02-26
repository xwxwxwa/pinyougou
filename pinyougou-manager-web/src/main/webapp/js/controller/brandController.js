		//设置controller 里面对于使用内嵌函数  参数为$scope   和  $http   其中scope是定义函数的  另外一个相当于ajax
		app.controller('brandController',function($scope,$http,$controller,brandService){
			
			//实现controller之间的继承  实际上就是scope之间的传递
			$controller('baseController',{$scope:$scope});
			
			//查询品牌列表
			$scope.findAll=function(){
				//成功之后的回调函数
				brandService.findAll().success(
						//接收成功之后返回的数据
					function(response){
						$scope.list=response;
					}		
				);				
			}
			
			
			
			
			//分页 
			$scope.findPage=function(page,size){
				brandService.findPage(page,size).success(
					function(response){
						$scope.list=response.rows;//显示当前页数据 	
						$scope.paginationConf.totalItems=response.total;//更新总记录数 
					}		
				);				
			}
			
			//查询实体  页面上的数据实体就是这里来的
			$scope.findOne=function(id){
				brandService.findOne(id).success(
					function(response){
						$scope.entity=response;
					}		
				);				
			}
			
			//新增
			$scope.save=function(){
				var object=null;
				if($scope.entity.id!=null){
					object=brandService.update($scope.entity);
				}else{
					object=brandService.add($scope.entity);
				}
				object.success(
					function(response){
						if(response.success){
							$scope.reloadList();//刷新
						}else{
							alert(response.message);
						}				
					}		
				);
			}
			
			
			
			//删除
			$scope.dele=function(){
				
					brandService.dele($scope.selectIds).success(
							function(response){
								if(response.success){//success里面是是否删除成功的Boolean值
									$scope.reloadList();//刷新
								}else{
									alert(response.message);
								}						
							}
					);
				
			}
			
			$scope.searchEntity={};
			//条件查询 
			$scope.search=function(page,size){
				
				brandService.search(page,size,$scope.searchEntity).success(
					function(response){
						$scope.list=response.rows;//显示当前页数据 	
						$scope.paginationConf.totalItems=response.total;//更新总记录数 
					}		
				);	
			}
		});