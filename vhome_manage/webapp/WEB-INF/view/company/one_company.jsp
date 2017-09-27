<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
//验证表单
var cn = true ;
  String.prototype.Trim = function() { 
  return this.replace(/(^\s*)|(\s*$)/g, "");};
//检查联系人
function check_companyPerson(){
    var username;
    username = document.getElementById('companyPerson').value;
    username = username.Trim();
    if (username == "") {
     document.getElementById('companyPerson_error').innerHTML = "<span style='color: red'>联系人不能为空</span>";
     } else {
	       document.getElementById('companyPerson_error').innerHTML = "";
     }
   }  
    //检查主营业务
 function check_companyBusiness(){
    	var username;
    	username = document.getElementById("companyBusiness").value;
    	username = username.Trim();
    	 if (username == "") {
    	     document.getElementById('companyBusiness_error').innerHTML = "<span style='color: red'>主营业务不能为空</span>";
    	     } else {
    		       document.getElementById('companyBusiness_error').innerHTML = "";
    	     }
    }
	//检查联系电话
	 function check_companyPhone()
{ 
 var telephone=document.getElementById("companyPhone").value; 
     telephone = telephone.Trim();
 if(telephone=="") 
 {  document.getElementById('companyPhone_error').innerHTML = "<span style='color: red'>请输入联系电话</span>";
      }else{ 
     var reg = /^[0-9]{11}$/i; 
     if(!reg.test(telephone)) { 
            document.getElementById('companyPhone_error').innerHTML="<span style='color: red'>请输入有效联系电话！例：13306191795</span>"; 
            
        }else{ 
      document.getElementById('companyPhone_error').innerHTML=""; 
      
     } 
	 }
 } 
	   //检查Email
	   function check_companyEmail(){
	     var email;
	     email = document.getElementById('companyEmail').value;
	     email = email.Trim();
	     if (email == "") {
	      document.getElementById('companyEmail_error').innerHTML = "<span style='color: red'>请输入Email</span>";
	      } else {
	       if (/^[\w-]+[\.]*[\w-]+[@][\w\-]{1,}([.]([\w\-]{1,})){1,3}$/.test(email)) {
	        document.getElementById('companyEmail_error').innerHTML = "";
	        } else {
	        document.getElementById('companyEmail_error').innerHTML = "<span style='color: red'>请输入有效的Email地址</span>";
	        }      
	      }
	     }
	   //整体检查
	    function regist(){
     var companyPerson_error=document.getElementById('companyPerson_error').innerHTML;
     var companyBusiness_error=document.getElementById('companyBusiness_error').innerHTML;
     var companyPhone_error=document.getElementById('companyPhone_error').innerHTML;
     var companyEmail_error=document.getElementById('companyEmail_error').innerHTML;
     var companyName=document.getElementById('companyName').value;
     var companyPerson=document.getElementById('companyPerson').value;
     var companyBusiness=document.getElementById('companyBusiness').value;
     var companyPhone=document.getElementById('companyPhone').value;
     var companyEmail=document.getElementById('companyEmail').value;     
     //判断错误信息全部为空并且文本框全部不为空
     if ((  companyPerson_error == "" && companyEmail_error==""&&
    		 companyBusiness_error == ""&& companyPhone_error =="")&&(
    		 companyPerson != "" && companyEmail !=""&&
    		 companyBusiness != ""&& companyPhone !=""	 
    		 )){
    	  $("#check").parents("form").submit();
 		  
         } else {
           alert ("注册信息填写错误，请仔细检查更正后再提交");      
         }
    
    
     } 
</script>

</head>
<body>
       <div style="margin-top: 10px;">
            <img alt="" src="images/agent020.png" width="15%">
           <hr align="center">
       </div>
     <div id="main" >
      <div id="table1" align="center">
	     <form id="form1" method="post" action="modifyCompany">
     <table >        
        <tr style="display: none;">
            <td><input name="companyId" value="${ac.companyId}"/></td>
        </tr>
        <tr style="line-height: 45px;">
		  <td align="right" valign="middle" width="150">公司名称：</td>
		  <td width="220"><input style="background-color: transparent; border: 0px;" type="text" name="companyName" id="companyName" class="_input"  value="${ac.companyName}" readonly="readonly" size="30px"/></td>
          <td id="companyName_error" class="after_input" width="250"></td>
		</tr>
		<tr style="line-height: 45px;">
		  <td align="right" valign="middle" width="150">联系人：</td>
		  <td  width="200"><input  type="text" name="companyPerson" id="companyPerson" class="_input" onblur="check_companyPerson()" value="${ac.companyPerson}"/></td>
          <td id="companyPerson_error" class="after_input" width="250"></td>
		</tr>
		<tr style="line-height: 45px;">
		  <td align="right" valign="middle" width="150">联系电话：</td>
		  <td  width="200"><input  type="text" name="companyPhone" id="companyPhone" class="_input" onblur="check_companyPhone()" value="${ac.companyPhone}"/></td>
          <td id="companyPhone_error" class="after_input" width="250"></td>
		</tr>
	    <tr style="line-height: 45px;">
		  <td align="right" valign="middle" width="150">电子邮箱：</td>
		  <td  width="200"><input type="text" name="companyEmail" id="companyEmail" class="_input" onblur="check_companyEmail()" value="${ac.companyEmail}"/></td>
          <td id="companyEmail_error" class="after_input" width="250"></td>
		</tr>
		<tr style="line-height: 45px;">
		  <td align="right" valign="middle" width="150">主营业务：</td>
		  <td  width="200"><input  type="text" name="companyBusiness" id="companyBusiness" class="_input" onblur="check_companyBusiness()"  value="${ac.companyBusiness}"/></td>
          <td id="companyBusiness_error" class="after_input" width="250"></td>
		</tr>
		<tr style="line-height: 45px;">
		  <td align="right" valign="middle" width="150">备注：</td>
		  <td> 
		    <textarea name="companyDescibe" cols="17" rows="3"  id="companyDescibe" >${ac.companyDescibe}</textarea>
		  </td>
		</tr>
		<tr style="line-height: 45px;">
		   <td>  <input name="companyId"  value="${ac.companyId}" style="display: none;" /></td>
		</tr>
		<tr>
		    		<td height="61" colspan="2" align="right">
						<button type="button" class="check" id="check"  onclick=" regist()">提交</button>
						<button type="button" class="close">取消</button></td>
		</tr>
	 </table>
	  </form>
	  </div>
  </div>

     <br />
	
</body>
</html>