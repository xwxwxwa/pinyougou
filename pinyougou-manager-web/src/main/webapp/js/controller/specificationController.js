 //控制层 
app.controller('specificationController' ,function($scope,$controller ,specificationService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
    //读取列表数据绑定到表单中  
	$scope.findAll=function(){
		specificationService.findAll().success(
			function(response){
				$scope.list=response;
			}			
		);
	}    
	
	//分页
	$scope.findPage=function(page,rows){			
		specificationService.findPage(page,rows).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//查询实体 
	$scope.findOne=function(id){				
		specificationService.findOne(id).success(
			function(response){
				$scope.entity= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		var serviceObject;//服务层对象  				
		if($scope.entity.specification.id!=null){//如果有ID
			serviceObject=specificationService.update( $scope.entity ); //修改  
		}else{
			serviceObject=specificationService.add( $scope.entity  );//增加 
		}				
		serviceObject.success(
			function(response){
				if(response.success){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		specificationService.dele( $scope.selectIds ).success(
			function(response){
				if(response.success){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.searchEntity={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		specificationService.search(page,rows,$scope.searchEntity).success(
			function(response){
				$scope.list=response.rows;	
				$scope.paginationConf.totalItems=response.total;//更新总记录数
			}			
		);
	}
	
	//增加规格的选项行
	//这里定义的话  需要下面这样定义  直接定义为空的话会报未定义的错误，这样定义就把entity的基本结构定下来了
	//$scope.entity={specificationOptionList:[]}
	$scope.addTableRow=function(){
		$scope.entity.specificationOptionList.push({});
	}
	
	//删除规格选项卡
	$scope.deleTableRow=function(index){
		//list删除元素的方法   index是起始位置  然后删除1个元素  批量元素删除
		$scope.entity.specificationOptionList.splice(index,1);
	}
	
	
    
});	
