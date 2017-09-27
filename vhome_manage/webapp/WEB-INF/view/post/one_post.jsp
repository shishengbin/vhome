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
	$(document).ready(function() {
		$("a").bind('click',function(){
			var url = $(this).attr("url");
			se(url);
		});		
	});
</script>
</head>
<body>
      <div id="table1" align="center">
	       <table  style="width: 100% ; margin-top: 50px">	       
             <tr>          
               <td align="center"><span style="font-size: 20px; font-weight: bolder;">${onePost.postname}</span></td>
             </tr>
            <tr><td><br/><br/></td></tr>
             <tr>                       
               <td align="center"><textarea name="textarea" cols="100" rows="25"  
               readonly="readonly" style="border-color:transparent;
                background-color: #F7F6EB ; overflow: auto;" >${onePost.postcontent} </textarea></td>
             </tr>
          </table>	    
	  </div>
     <br/>	
</body>
</html>