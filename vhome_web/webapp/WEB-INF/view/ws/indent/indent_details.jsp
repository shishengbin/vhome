<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../pageControl/jstlImport.jsp"%>
<link href="${pageContext.request.contextPath}/css/indent.css" rel="stylesheet" type="text/css" media="screen"/>
</head>
<body>
<div class="idlb">
		<table>
			<thead>
				<tr>
					<th width="12%"></th>
					<th width="22%">商品名称</th>
					<th width="12%">商品售价</th>
					<th width="12%">商品数量</th>
					<th width="12%">所属分类</th>
					<th width="22%">商品产地</th>
				</tr>
				<tr>
					<td colspan="5"><dl>
							<dt>订单编号：</dt>
							<dd class="title">${indent.number }（${indent.name }）</dd>
						</dl></td>
					<td><dl>
							<dt>总价：</dt>
							<dd class="price">￥${indent.money }</dd>
						</dl></td>
				</tr>
				<tr>
					<td colspan="6">
					<dl>
							<%-- <dt>付款方式：</dt>
							<dd class="showbor">${indent.payment.name }</dd> --%>
							<dt>下单时间：</dt>
							<dd class="showbor"><fmt:formatDate value="${indent.createTime }" pattern="yyyy-MM-dd HH:mm"/></dd>
							<dt>交易状态：</dt>
							<dd style="color: red">
								<c:choose>
									<c:when test="${indent.status ==0}">
										<c:out value="未付款."></c:out>
									</c:when>
									<c:when test="${indent.status ==1}">
										<c:out value="已付款待发货."></c:out>
									</c:when>
									<c:when test="${indent.status ==2}">
										<c:out value="待收货."></c:out>
									</c:when>
									<c:when test="${indent.status ==3}">
										<c:out value="交易关闭."></c:out>
									</c:when>
									<c:when test="${indent.status ==4}">
										<c:out value="交易成功."></c:out>
									</c:when>
								</c:choose>
							</dd>
						</dl></td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${indent.productIndnet }" var="pi">
					<tr>
						<td class="hed">
						<div class="imh">
							<img alt="" src="ws/showImg?id=${pi.product.picture[0].id }">
						</div>
						</td>
						<td width="188px;">${pi.product.productName }</td>
						<td width="188px;">${pi.product.productPrice }</td>
						<td width="188px;">${pi.count }</td>
						<td width="188px;"></td>
						<td width="180px;">${pi.product.productCity }</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="6"><dl>
							<%-- <dt>用户账号：</dt>
							<dd class="showbor">${indent.human.human_account }</dd> --%>
							<dt>商家名称：</dt>
							<dd class="showbor">${indent.remark }</dd>
							<dt>收货人：</dt>
							<dd class="showbor">${indent.receivePerson }</dd>
							<dt>收货电话：</dt>
							<dd class="showbor">${indent.receivePhone }</dd>
							<dt>物流公司：</dt>
							<dd class="showbor">
							<c:choose>
								<c:when test="${indent.transportName=='zhongtong' }">
									<c:out value="中通快递"/>
								</c:when>
								<c:when test="${indent.transportName=='shentong' }">
									<c:out value="申通E物流 "/>
								</c:when>
								<c:when test="${indent.transportName=='yuantong' }">
									<c:out value="圆通快递"/>
								</c:when>
								<c:when test="${indent.transportName=='yunda' }">
									<c:out value="韵达快运"/>
								</c:when>
								<c:when test="${indent.transportName=='huitongkuaidi' }">
									<c:out value="汇通快运"/>
								</c:when>
								<c:when test="${indent.transportName=='shunfeng' }">
									<c:out value="顺丰快递"/>
								</c:when>
								<c:when test="${indent.transportName=='ems' }">
									<c:out value="EMS"/>
								</c:when>
							</c:choose>						
							</dd>
							<dt>运&nbsp;单&nbsp;号：</dt>
							<dd>${indent.transportNumber }</dd>
						</dl>
						<dl>
							<dt>收货地址：</dt>
							<dd>${indent.transportDestination }</dd>
						</dl></td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>