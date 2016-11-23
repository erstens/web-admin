/**
 * Created by wang'ao on 2016/11/14 0014.
 */
//表单序列化成对象
jQuery.prototype.serializeObject=function(){
    var obj=new Object();
    $.each(this.serializeArray(),function(index,param){
        if(!(param.name in obj)){
            obj[param.name]=param.value;
        }
    });
    return obj;
};