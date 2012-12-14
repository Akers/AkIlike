//自加载内容：
$(function(){
    //ajax 获得员工下拉列表内容
    $("#employeeList").ready(
        function(e){
            $.ajax({
                type:"post",
                url:"/jee_class_design/ajax",
                data:{m:"genl"},
                success:function(data){
                    //清空下拉框
                    $("#employeeList").empty();
                    //添加下拉选项
                    for(var i=0;i<data.length;i++)
                    {
                        var option = "<option value="+data[i].userID+">"+data[i].userName+"</option>"
                        //$("#employeeList").append(option);
                        $(option).appendTo("#employeeList");
                    }
                },
                dataType:"json"
            });
        }
    );
    //返回按钮：
    $("#btn_back").bind("click", function(){
        window.history.go(-1) ;//返回上一页
    });
});

