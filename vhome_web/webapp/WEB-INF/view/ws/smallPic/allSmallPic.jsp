<%@ page language="java"  import="java.util.Random" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
	<%@ include file="../pageControl/jstlImport.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="now" class="java.util.Date" /> 
<style type="text/css">
.a1{
	width: 600px;
	margin-top: -10px;
	margin-bottom: 5px;
}
.rowtd2 img{
	width: 100%;
	height: 100%;
	
}
.rowtd1 img{
	width: 100%;
	height: 100%;
}
.tableRow{
width:600px;
}
.rowtd1{
	list-style:none;
	height: 150px;
	width:38%;
}
.rowtd2{
list-style:none;
	height: 150px;
	width:58%;
}
.rowtd3{
	width:1%;
}
.rowtr{
	width: 600px;
	height: 150px;
}
img{
	border: none;
}
</style>
<script type="text/javascript">
function updateSmallPic(id1,id2,row)
{	
		navTab.openTab('page16','ws/querySmallPicById_liu', {title:'修改小图广告位', fresh:true, data:{'id1':id1,'id2':id2,'row':row}});
}
function deleteSmallPic(index)
{	
	 var divD = document.getElementById("divRow"+index);
	 $.ajax({
			url : "ws/deleteSmallPic",
			type : "get",
			dataType : "json",
			data : {"index" : index},
			success : function(data) {
				if(data.OK==1)
				{
					 divD.style.display = "none";
					 var nextRow=index-2;
					 if(nextRow!=-2)
				     {
						 var newDelRow = document.getElementById("delRow"+nextRow); 
						 newDelRow.style.display = "block";
				     }
				}
				else
					alert("删除失败");
			},
			error:function(){
				alert("删除失败");
			}
		});
}
function addSmallPic(row){
	navTab.openTab('page16','ws/addSmallPic', {title:'增加小图广告位', fresh:false, data:{'row':row}});
}
</script>
   <p class="contentTitle">在这里，您可以添加首页小图广告位</p>
	<div layoutH="98" style="float: left; display: block; margin: 10px; overflow: auto; width: 99%; border: solid 0px #CCC; line-height: 21px; background: #FFF;">	
	<c:set var="nowRow" value="0"></c:set>
	<c:forEach items="${piclist}" var="tt" varStatus="index">
			<c:if test="${index.index%2==0}">
				<c:set var="nowRow" value="${index.index/2+1}">
				</c:set>
				<%
					Random r=new Random();
					session.setAttribute("nowT", r.nextLong());
    			%>
    			<div id="divRow${index.index}">
				<div class = "a1" >
					<table class="tableRow" cellspacing="0px" cellpadding="0px">
						<tr class="rowtr">
							<c:if test="${nowRow%2==1}">
								<td class="rowtd2">
									<img src="ws/showSmallPic?id=${piclist.get((nowRow-1)*2).smallPic_id}&t=${nowT}">
								</td>
								<td class="rowtd3"></td>
								<td class="rowtd1">
									<img src="ws/showSmallPic?id=${piclist.get(2*nowRow-1).smallPic_id}&t=${nowT}">
								</td>
							</c:if>
							<c:if test="${nowRow%2==0}">
								<td class="rowtd1">
									<img src="ws/showSmallPic?id=${piclist.get((nowRow-1)*2).smallPic_id}&t=${nowT}">
								</td>
								<td class="rowtd3"></td>
								<td class="rowtd2">
									<img src="ws/showSmallPic?id=${piclist.get(2*nowRow-1).smallPic_id}&t=${nowT}">
								</td>
							</c:if>
						<tr>
					</table>
				</div>
				<div class="buttonActive" onclick="updateSmallPic(${piclist.get((nowRow-1)*2).smallPic_id},${piclist.get(2*nowRow-1).smallPic_id},${nowRow})"><div class="buttonContent"><button>修改</button></div></div>
				<c:if test="${index.index+2==piclist.size()}">
					<div id="delRow${index.index}" style="display: block;"><div class="buttonActive"   onclick="deleteSmallPic(${index.index})"><div class="buttonContent"><button>删除</button></div></div></div>
				</c:if>
				<c:if test="${index.index+2!=piclist.size()}">
					<div id="delRow${index.index}" style="display: none;"><div class="buttonActive"   onclick="deleteSmallPic(${index.index})"><div class="buttonContent"><button>删除</button></div></div></div>
				</c:if>
				<br ><br ><br >
				</div>
				
			</c:if>
	</c:forEach>
	<div class="buttonActive" onclick="addSmallPic(${nowRow})"><div class="buttonContent"><button>增加一行</button></div></div>
	</div>