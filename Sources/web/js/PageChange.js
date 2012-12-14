$(function(){
    //取得参数串
    var params = $("#condition").val();
    //取得当前页：
    var curPage = params.substr(params.indexOf("[")+1, 1);
    //取得尾页码
    var lsPage = parseInt($("#total").html());
    //首页
    $("#goFrist").bind("click",function(){
        var url = "../../../assline?"+params.substring(0, params.indexOf("["));
        window.location.href = url;
    });
    
    //上一页
    $("#goPre").bind("click",function(){
        if(curPage == 1)
        {
            alert("已是第一页");
            return false;
        }
        else
            curPage = parseInt(curPage);
        var url = "../../../assline?"+params.substring(0, params.indexOf("["))+"&page="+(curPage-1);
        window.location.href = url;
    });
    
    //下一页
    $("#goNext").bind("click",function(){
        if(curPage == lsPage)
        {
            alert("已是最后一页");
            return false;
        }
        else
            curPage = parseInt(curPage);
        var url = "../../../assline?"+params.substring(0, params.indexOf("["))+"&page="+(curPage+1);
        window.location.href = url;
    });
    
    //尾页
    $("#goLast").bind("click",function(){
        var url = "../../../assline?"+params.substring(0, params.indexOf("["))+"&page="+lsPage;
        window.location.href = url;
    });
    
    //显示当前页
    $("#current").html(curPage);
    
    //页数下拉列表
    $("#pageSelect")
        .val(curPage)//默认选中当前页
        .bind("change", function(){
                var url = "../../../assline?"+params.substring(0, params.indexOf("["))+"&page="+$(this).val();
                window.location.href = url;
            }
        )
    ;
});



