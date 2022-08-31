<%--
  Created by IntelliJ IDEA.
  User: Dzmitry
  Date: 08.08.2022
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="fragments/header.jsp"/>
<head>
    <title>My Orders | Online store</title>
    <jsp:include page="fragments/headerRefs.jsp"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization.language" var="loc"/>
<div class="p-3">
    <div class="row justify-content-center">
        <c:if test="${sessionScope.user == null}">
            <div class="alert alert-danger fade show" role="alert">
                <fmt:message bundle="${loc}" key="language.noRights"/>
            </div>
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col"><fmt:message bundle="${loc}" key="language.orderId"/></th>
                    <th scope="col"><fmt:message bundle="${loc}" key="language.title"/></th>
                    <th scope="col"><fmt:message bundle="${loc}" key="language.number"/></th>
                    <th scope="col"><fmt:message bundle="${loc}" key="language.address"/></th>
                    <th scope="col"><fmt:message bundle="${loc}" key="language.orderDate"/></th>
                    <th scope="col"><fmt:message bundle="${loc}" key="language.deliveryDate"/></th>
                    <th scope="col"><fmt:message bundle="${loc}" key="language.status"/></th>
                </tr>
                </thead>
                <c:forEach var="orderInformation" items="${ordersInformation}">
                    <tr>
                        <th scope="row">#<c:out value="${orderInformation.id}"/></th>
                        <td class="text-primary">
                            <c:forEach var="order" items="${ordersFromUser}">
                                <c:forEach var="product" items="${products}">
                                    <c:if test="${product.id == order.productId && orderInformation.id == order.orderInformationId}">
                                        <p><c:out value="${product.productName}"/></p>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                        </td>
                        <td class="text-primary">
                            <c:forEach var="order" items="${ordersFromUser}">
                                <c:if test="${orderInformation.id == order.orderInformationId}">
                                    <p><c:out value="${order.purchaseQuantity}"/></p>
                                </c:if>
                            </c:forEach>
                        </td>
                        <td><c:out value="${orderInformation.deliveryAddress}"/></td>
                        <td><c:out value="${orderInformation.orderDate}"/></td>
                        <td><c:out value="${orderInformation.deliveryDate}"/></td>
                        <c:if test="${orderInformation.orderStatus == 'доставлен'}">
                            <td class="text-success"><c:out value="${orderInformation.orderStatus}"/></td>
                        </c:if>
                        <c:if test="${orderInformation.orderStatus == 'ожидается'}">
                            <td class="text-active"><c:out value="${orderInformation.orderStatus}"/></td>
                        </c:if>
                        <c:if test="${orderInformation.orderStatus == 'отменен'}">
                            <td class="text-danger"><c:out value="${orderInformation.orderStatus}"/></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>
</body>
<jsp:include page="fragments/footer.jsp"/>
</html>
