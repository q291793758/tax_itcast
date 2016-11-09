<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div class="c_pate" style="margin-top: 5px;">
    <!-- 如果页数为空不显示分页选项 -->
    <s:if test="pageResult.totalCount > 0">
        <table width="100%" class="pageDown" border="0" cellspacing="0"
               cellpadding="0">
            <tr>
                <td align="right">
                    总共<s:property value="pageResult.totalCount"/>条记录，当前第 <s:property value="pageResult.pageNo"/> 页，
                    共 <s:property value="pageResult.totalPageCount"/> 页
                    <!-- 非第一页才有“上一页”选项 -->
                    <s:if test="pageResult.pageNo>1">
                        <a href="javascript:doGoPage(<s:property value='pageResult.pageNo-1'/>)">上一页</a>
                    </s:if>
                    <!-- 非最后一页才有“下一页”选项 -->
                    <s:if test="pageResult.pageNo < pageResult.totalPageCount">
                        <a href="javascript:doGoPage(<s:property value='pageResult.pageNo+1'/>)">下一页</a>
                    </s:if>
                    到 <input id="pageNo" name="pageNo" type="text" style="width: 30px;" onkeypress="if(event.keyCode == 13){doGoPage(this.value);}" min="1"
                             max="" value="<s:property value="pageResult.pageNo"/>" />
                </td>
            </tr>
        </table>
    </s:if><s:else>暂无数据!</s:else>
</div>
<script type="text/javascript">
    //翻页方法
    function doGoPage(pageNo){
        document.getElementById("pageNo").value = pageNo;
        document.forms[0].action=list_url;
        document.forms[0].submit();
    }
</script>
