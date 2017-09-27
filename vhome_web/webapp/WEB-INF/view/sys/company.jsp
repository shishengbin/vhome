<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8" trimDirectiveWhitespaces="true"%>
<%@ include file="../ws/pageControl/jstlImport.jsp"%>
<div class="pageContent">
	<form action="sys/updateCompanyInfo" method="post"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,navTabAjaxDone)">
		<p class="contentTitle">
			<img alt="" src="images/qiye.png">
		</p>
		<div class="pageFormContent" layoutH="98" style="margin-left: 20px">
			<input type="hidden" name="id" value="${companyinfo.id }" />

			<div class="unit">
				<label>企业名称：</label> <input type="text" name="company_name"
					maxlength="50" size="40" value="${companyinfo.companyname }"
					readonly="readonly" />
			</div>
			<br>
			<br>
			<div class="divider"></div>
			<div class="unit">
				<label>企业地址：</label> <input type="text" name="company_address"
					maxlength="50" size="40" value="${companyinfo.companyaddress }" />
				<span class="info"></span>
			</div>
			<br>
			<br>
			<div class="divider"></div>
			<div class="unit">
				<label>企业邮箱：</label> <input type="text" name="company_email"
					maxlength="50" size="40" value="${companyinfo.companyemail }" />
			</div>
			<br>
			<br>
			<div class="divider"></div>
			<div class="unit">
				<label>联系人：</label> <input type="text" name="company_person"
					maxlength="50" size="40" value="${companyinfo.companyperson }" />
			</div>
			<br>
			<br>
			<div class="divider"></div>
			<div class="unit">
				<label>联系电话：</label> <input type="text" name="company_phone"
					maxlength="50" size="40" class="phone"
					value="${companyinfo.companyphone }" />
			</div>
			<br>
			<br>
			<div class="divider"></div>
			<div class="unit">
				<label>传真：</label> <input type="text" name="company_fax"
					maxlength="50" size="40" value="${companyinfo.companyfax }" />
			</div>
			<br>
			<br>
			<div class="divider"></div>
			<div class="unit">
				<label>官网：</label> <input type="text" name="company_site"
					maxlength="50" size="40" value="${companyinfo.companysite }" />
			</div>
			<br>
			<br>
			<div class="divider"></div>
			<div class="unit">
				<label>主营业务：</label>
				<textarea name="company_business" cols="46" rows="6">${companyinfo.companybusiness }</textarea>
			</div>
			<br>
			<br>
			<div class="divider"></div>
			<div class="unit">
				<label>备注：</label>
				<textarea name="company_describe" cols="46" rows="6">${companyinfo.companydescribe }</textarea>
			</div>
			<br>
			<br>
			<div class="divider"></div>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div></li>
				<li><div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>
