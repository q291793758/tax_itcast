<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp" %>
    <title>用户管理</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/datepicker/WdatePicker.js"></script>

</head>
<body class="rightBody">
<form id="form" name="form" action="${pageContext.request.contextPath}/nsfw/user_edit.action" method="post"
      enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
                <div class="c_crumbs">
                    <div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;编辑用户</div>
                </div>
                <div class="tableH2">编辑用户</div>
                <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0"
                       cellspacing="0">
                    <tr>
                        <td class="tdBg" width="200px">所属部门：</td>
                        <td><s:select name="user.dept" list="#{'部门A':'部门A','部门B':'部门B'}"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">头像：</td>
                        <td>
                            <%--ognl表达式计算是否更改头像--%>
                            <s:if test="%{user.headImg != null && user.headImg != ''}">
                            <img src="${basePath }upload/<s:property value='user.headImg'/>" width="100" height="100"/>
                            <s:hidden name="user.headImg"/>
                        </s:if>
                            <input type="file" name="headImg"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">用户名：</td>
                        <td><s:textfield id="name" name="user.name"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">帐号：</td>
                        <td><s:textfield id="account" name="user.account" onchange="doVerify()"/><p id="pID"></p> </td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">密码：</td>
                        <td><s:textfield id="password" name="user.password"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">性别：</td>
                        <td><s:radio list="#{'true':'男','false':'女'}" name="user.gender"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">角色：</td>
                        <td>
                            <s:checkboxlist list="#roleList" name="userRoleIds" listKey="roleId" listValue="name" />
                        </td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">电子邮箱：</td>
                        <td><s:textfield name="user.email"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">手机号：</td>
                        <td><s:textfield name="user.mobile"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">生日：</td>
                        <td><s:textfield id="birthday" name="user.birthday" readonly="true"
                                         onfocus="WdatePicker({'skin':'whyGreen','dateFmt':'yyyy-MM-dd'});">
                            <s:param name="value"><s:date name="user.birthday" format="yyyy-MM-dd"/></s:param>
                        </s:textfield></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">状态：</td>
                        <td><s:radio list="#{'1':'有效','0':'无效'}" name="user.state"/></td>
                    </tr>
                    <tr>
                        <td class="tdBg" width="200px">备注：</td>
                        <td><s:textarea name="user.memo" cols="75" rows="3"/></td>
                    </tr>
                </table>
                <s:hidden name="user.id"/>
                <s:hidden name="searchString"/>
                <div class="tc mt20">
                    <input type="button" class="btnB2" value="保存" onclick="doSubmit()"/>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="button" onclick="javascript:history.go(-1)" class="btnB2" value="返回"/>
                </div>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript">
    //校验账号唯一
    var vResult = false;
    function doVerify() {
        //1获得账号value
        var account = $("#account").val();

        if (account != null) {
            //2 AJAX校验唯一性
            $.ajax({
                url: "${pageContext.request.contextPath}/nsfw/user_verifyAccount.action",
                data: {"user.account": account,"user.id":"${user.id}"},
                type: "POST",
                async: false,//非异步
                success: function (msg) {
                    //根据服务器返回数据判断是否唯一 msg="true":数据库已存在
                    if ("true" != msg) {
                        $("#pID").text("账号已存在，请使用其他账号！");
                        //清空账号框，并获得焦点
                        $("#account").val("");
                        vResult = false;
                    } else {
                        vResult = true;
                    }
                }

            });
        }

    }

    //提交校验
    function doSubmit() {
        var name = $("#name");
        if (name.val() == "") {
            alert("用户名不能为空！");
            name.focus();
            return false;
        }
        var account1 = $("#account");
        if (account1.val() == "") {
            alert("账户不能为空！");
            account1.focus();
            return false;
        }
        var password = $("#password");
        if (password.val() == "") {
            alert("密码不能为空！");
            password.focus();
            return false;
        }
        //再进行帐号校验
        doVerify();
        if (vResult) {
            //提交表单
            document.forms[0].submit();
        }

    }

    //account获得焦点清空提示
    $("#account").focus(function () {
        $("#pID").text("");
    })

</script>
</body>
</html>