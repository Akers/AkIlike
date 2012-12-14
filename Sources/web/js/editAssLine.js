$(function(){
    //ajax 获得下拉列表内容
    $("#bankAccountIDList").ready(function(e){
        //银行帐号列表
        $.ajax({
            type:"post",
            url:"/jee_class_design/ajax",
            data:{m:"gbail"},
            success:function(data){
                //添加下拉选项
                for(var i=0;i<data.length;i++)
                {
                    var option = "<option value="+data[i].bankAccountID+">"+data[i].bankAccount+"|"+data[i].bankAccountName+"</option>"
                    //$("#employeeList").append(option);
                    $(option).appendTo("#bankAccountIDList");
                }
            },
            dataType:"json"
        });
    });
    //客户列表
    $("#customerList").ready(function(e){
        $.ajax({
            type:"post",
            url:"/jee_class_design/ajax",
            data:{m:"gcnil"},
            success:function(data){
                //添加下拉选项
                for(var i=0;i<data.length;i++)
                {
                    var option = "<option value="+data[i].customerID+">"+data[i].customerName+"</option>"
                    //$("#employeeList").append(option);
                    $(option).appendTo("#customerList");
                }
            },
            dataType:"json"
        });
    })
    .change(function(){
        if($(this).val() > 0)
            $("#supplierList").val(0);
    });
    //供应商列表
    $("#supplierList").ready(function(e){
        $.ajax({
            type:"post",
            url:"/jee_class_design/ajax",
            data:{m:"gsnil"},
            success:function(data){
                //添加下拉选项
                for(var i=0;i<data.length;i++)
                {
                    var option = "<option value="+data[i].supplierID+">"+data[i].supplierName+"</option>"
                    //$("#employeeList").append(option);
                    $(option).appendTo("#supplierList");
                }
            },
            dataType:"json"
        });
    })
    .change(function(){
        if($(this).val() > 0)
            $("#customerList").val(0);
    });
    
    $(".delAssLineHref").bind("click", function(){
        var alid = $(this).parent().parent().parent().children(".bankAccountAssLineID").children().html();
        if(confirm("您确定要删除序号为"+alid+"的账户明细记录吗？"))
            window.location.href = "../../../assline?m=del&alid="+alid;
        else
            return false;
    });
});
