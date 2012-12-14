// JavaScript Document

function delAccount(accountID)
{
    var formName = "formAccount_" + accountID;
    if(confirm("您确定要删除序号为"+accountID+"的账户？"))
    {
        document.getElementsByName(formName)[0].submit();
        return true;
    }
    else
        return false;
}

function oderNewItem_slt(){
document.form1.action="oderNewItem_slt.html"
document.form1.target="bottom"
}

function productMainClass_add(){
document.productMainClass.action="productMainClass_add.html"
document.productMainClass.target="bottom"
}

function productMainClass_edit(){
document.productMainClass.action="productMainClass_edit.html"
document.productMainClass.target="bottom"
}


function productSubClass_add(){
document.productSubClass.action="productSubClass_add.html"
document.productSubClass.target="bottom"
}

function productSubClass_edit(){
document.productSubClass.action="productSubClass_edit.html"
document.productSubClass.target="bottom"
}


function orderSendAddr_add(){
document.orderSendAddr.action="orderSendAddr_add.html"
document.orderSendAddr.target="bottom"
}

function orderSendAddr_edit(){
document.orderSendAddr.action="orderSendAddr_edit.html"
document.orderSendAddr.target="bottom"
}

function roseModelAccess_add(){
document.roseModelAccess.action="roseModelAccess_add.html"
document.roseModelAccess.target="bottom"
}

function roseModelAccess_edit(){
document.roseModelAccess.action="roseModelAccess_edit.html"
document.roseModelAccess.target="bottom"
}

function employee_add(){
document.employee.action="employee_add.html"
document.employee.target="bottom"
}

function employee_edit(){
document.employee.action="employee_edit.html"
document.employee.target="bottom"
}


function department_add(){
document.department.action="department_add.html"
document.department.target="bottom"
}

function department_edit(){
document.department.action="department_edit.html"
document.department.target="bottom"
}


function rose_add(){
document.rose.action="rose_add.html"
document.rose.target="bottom"
}

function rose_edit(){
document.rose.action="rose_edit.html"
document.rose.target="bottom"
}



function modelSub_add(){
document.modelSub.action="model_add.html"
document.modelSub.target="bottom"
}

function modelSub_edit(){
document.modelSub.action="model_edit.html"
document.modelSub.target="bottom"
}


function business_add(){
document.business.action="business_add.html"
document.business.target="bottom"
}

function business_edit(){
document.business.action="business_edit.html"
document.business.target="bottom"
}


/**
 * 表单检查函数
 */
function checkForm()
{
    var flag = true;
    //初始化错误提醒：
    $("#txt_bankName").parent().children(".errMsgBox").hide();
    $("#txt_accountName").parent().children(".errMsgBox").hide();
    $("#txt_bankAccount").parent().children(".errMsgBox").hide();
    $("#txt_bankBalance").parent().children(".errMsgBox").hide();
    $("#txt_bankAccountCashBalance").parent().children(".errMsgBox").hide();
    
    var patrn = null;
    
    //验证银行名
    patrn=/["'?&#$=]+/;//过滤特殊字符
    if($("#txt_bankName").val() == "")
    {
        $("#txt_bankName")
            .focus()
            .parent()
            .children(".errMsgBox")
            .show()
            .children("span")
            .html("银行名不能为空");
        flag = false;
    }
    else if(patrn.exec($("#txt_bankName").val()))
    {
        $("#txt_bankName")
            .focus()
            .parent()
            .children(".errMsgBox")
            .show()
            .children("span")
            .html("银行名不能包含\"'?&#$=等特殊字符");
        flag = false;
    }
    
    //验证账户名
    if($("#txt_accountName").val() == "")
    {
        $("#txt_accountName")
            .focus()
            .parent()
            .children(".errMsgBox")
            .show()
            .children("span")
            .html("账户名不能为空");
        flag = false;
    }
    else if(patrn.exec($("#txt_accountName").val()))
    {
        $("#txt_accountName")
            .focus()
            .parent()
            .children(".errMsgBox")
            .show()
            .children("span")
            .html("账户名不能包含\"'?&#$=等特殊字符");
        flag = false;
    }
    
    //验证账户输入
    patrn=/[0-9 A-Z a-z]+/;
    if($("#txt_bankAccount").val() == "")
    {
        $("#txt_bankAccount")
            .focus()
            .parent()
            .children(".errMsgBox")
            .show()
            .children("span")
            .html("账户号不能为空");
        flag = false;
    }
    else if(!patrn.exec($("#txt_bankAccount").val()))
    {
        $("#txt_bankAccount")
            .focus()
            .parent()
            .children(".errMsgBox")
            .show()
            .children("span")
            .html("账户号只能由数字、大小写字母组成");
        flag = false;
    }
    
    if($("#txt_bankBalance").val() == "")
    {
        $("#txt_bankBalance")
            .focus()
            .parent()
            .children(".errMsgBox")
            .show()
            .children("span")
            .html("账户余额不能为空");
        flag = false;
    }
    
     if(isNaN($("#txt_bankBalance").val()))
    {
        $("#txt_bankBalance")
            .focus()
            .parent()
            .children(".errMsgBox")
            .show()
            .children("span")
            .html("账户余额必须是一个数字");
        flag = false;
    }
    
    if($("#txt_bankAccountCashBalance").val() == "")
    {
        $("#txt_bankAccountCashBalance")
            .focus()
            .parent()
            .children(".errMsgBox")
            .show()
            .children("span")
            .html("现金余额不能为空");
        flag = false;
    }
    
    if(isNaN($("#txt_bankAccountCashBalance").val()))
    {
        $("#txt_bankAccountCashBalance")
            .focus()
            .parent()
            .children(".errMsgBox")
            .show()
            .children("span")
            .html("现金余额必须是一个数字");
        flag = false;
    }
    
    if(flag == true)
        return true;
    else
        return false;
}
	