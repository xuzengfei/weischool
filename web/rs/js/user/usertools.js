/***
 * 元素主键
 */
var elementId;
var elemntName;
/**
 * 存放id数组
 * @type {Array}
 */
var stObj ={};

/**
 * 获得账号
 * @param no
 */
function  setNo(no){
  $("#"+elementId).val(no);
}
/**
 * 设置ID 和名称
 * @param id
 * @param name
 */
function setId(id,name){
    $("#"+elementId).val(id);
    $("#"+elemntName).val(name);
}
/**
 *设置数组值
 * @param id
 * @param name
 */
function setStObj(id,name){
	 
    stObj[id] =name;
}
/**
 * 删除对象
 * @param id
 */
function reStObj(id){
	 
    delete stObj[id];
}
/**
 * 清空对象
 */
function reAllStObj() {
    stObj={};
}
/**
 * 获得对象值存返回到界面中
 */
function  getStObj(){
    var ids ="";
    var names="";
    for(var obj in stObj){
        ids+=","+obj;
        names+=","+stObj[obj];
    }
    if(ids!=""){
    	ids =ids.substring(1);
    	names=names.substring(1);
    }
    setId(ids,names);
} 
function returnStObj(){
	return stObj;
}
/**
 * 打开学生选人窗口
 * @param e_id
 */
function radioSSelect(e_id){
    elementId =e_id;
   openWin(basePath+"web/manager/utools/to/st/0","单选",500,400); 
}
/**
 * 多选学生
 * @param e_id
 * @param e_name
 */
function checkboxSSelect(e_id,e_name){
	elementId =e_id;
    elemntName=e_name;
    var ids = $("#"+elementId).val();
    var nams= $("#"+elemntName).val();
    if(ids!=""){
    	var idsArray =ids.split(",");
    	var namsArray =nams.split(",");
    	for(var i=0;i<idsArray.length;i++){
    		setStObj(idsArray[i],namsArray[i]);
    	}
    }
    layer.open({
        type: 2,
        scrollbar: false,
        title:"多选",
        content: basePath+"web/manager/utools/to/st/1",
        area: ['500px', '400px'],
        maxmin: false,
        btn: ['确定', '取消'],
        yes: function(index, layero){
        	getStObj();
           layer.close(index); //如果设定了yes回调，需进行手工关闭
          },
          cancel: function(index){ 
        	  stObj={};//清空对象
        	  layer.close(index); //如果设定了yes回调，需进行手工关闭
         } 
    });
}
/**
 * 单选学生
 * @param e_id
 * @param e_name
 */
function radioSSelect1(e_id,e_name){
    elementId =e_id;
    elemntName=e_name;
    var id = $("#"+elementId).val();
    var name= $("#"+elemntName).val();
    if(id!=""){
        setStObj(id,name);
    }
    layer.open({
        type: 2,
        scrollbar: false,
        title:"单选",
        content: basePath+"web/manager/utools/to/st/2",
        area: ['500px', '400px'],
        maxmin: false,
        btn: ['确定', '取消'],
        yes: function(index, layero){
            getStObj();
            layer.close(index); //如果设定了yes回调，需进行手工关闭
        },
        cancel: function(index){
            reAllStObj();//清空对象
            layer.close(index); //如果设定了yes回调，需进行手工关闭
        }
    });
}

/**
 * 打开老师选人窗口
 * @param e_id
 * @param e_name
 */
function radioTSelect(e_id,e_name){
    elementId =e_id;
    elemntName=e_name;
    openWin(basePath+"web/manager/utools/to/tc/0","单选",500,400);
}