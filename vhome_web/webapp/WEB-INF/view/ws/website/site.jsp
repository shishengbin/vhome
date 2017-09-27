<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<form action="" method="post"
	class="pageForm required-validate" 	onsubmit="return validateCallback(this,navTabAjaxDone)">
	<div class="pageContent">
		<table class="list" width="100%" layoutH="38" >		
			<thead>
			<tr>
				<th width="10%" ></th>
				<th width="20%" ></th>
				<th width="30%" ></th>
			</tr>
			</thead>						
			<tbody>
			<tr>
				<td colspan="4"><input type="hidden" name="id" value=""></td>
			</tr>
												
			<tr>
				<td height="30" align="right" >公司名称：</td>
				<td height="30" ><input type="text" name="company_name" maxlength="50" size="40" class="required" /></td>
				<td height="30" >公司或单位名称</td>
			</tr>
			<tr>
				<td height="30" align="right" bgcolor="#f2f2f2" >公司地址：</td>
				<td height="30" bgcolor="#f2f2f2"><input type="text" name="company_address" maxlength="50" size="40" class="required" /></td>
				<td height="30" bgcolor="#f2f2f2" >公司所在地址</td>
			</tr>
			<tr>
				<td height="30" align="right" >E-Mail：</td>
				<td height="30"><input type="text" name="company_email" maxlength="50" size="40" class="required email" /></td>
				<td height="30" >E-mail邮箱</td>
			</tr>
			<tr>
				<td height="30" align="right" bgcolor="#F2F2F2" >联系人：</td>
				<td height="30" bgcolor="#F2F2F2"><input type="text" name="company_person" maxlength="50" size="40" class="required" /></td>
				<td height="30" bgcolor="#F2F2F2"><span >设置网站联系人</span></td>
			</tr>
			<tr>
				<td height="30" align="right" >联系电话：</td>
				<td height="30"><input type="text" name="company_phone" maxlength="50" size="40" class="phone" /></td>
				<td height="30"><span >设置联系人电话</span></td>
			</tr>
			<tr>
				<td height="30" align="right" bgcolor="#f2f2f2" >传真：</td>
				<td height="30" bgcolor="#f2f2f2"><input type="text" name="company_name" maxlength="50" size="40" class="digits" /></td>
				<td height="30" bgcolor="#f2f2f2"><span >传真号码</span></td>
			</tr>
			<tr>
				<td height="30" align="right">官网：</td>
				<td height="30"><input type="text" name="company_name" maxlength="50" size="40"/></td>
				<td height="30" >设置官方网址</td>
			</tr>
			<tr>
				<td height="30" align="right" bgcolor="#f2f2f2" >主营业务：</td>
				<td height="30" bgcolor="#f2f2f2"><input type="text" name="company_name" maxlength="50" size="40" /></td>
				<td height="30" bgcolor="#f2f2f2" >设置公司的主营业务</td>
			</tr>
			<tr>
				<td height="30" align="right" >备注：</td>
				<td height="30"><input type="text" name="company_name" maxlength="50" size="40" /></td>
				<td height="30" >备注信息</td>
			</tr>	
			</tbody>				
		</table>
		<c:import url="../pageControl/submitButton.jsp"></c:import>
	</div>
</form>