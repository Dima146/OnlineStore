<%--
  Created by IntelliJ IDEA.
  User: Dzmitry
  Date: 08.08.2022
  Time: 13:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
  <jsp:include page="fragments/header.jsp"/>
  <title>View orders | Online store</title>
</head>
<body>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization.language" var="loc"/>

<c:if test="${sessionScope.role.roleName != 'admin'}">
  <div class="container">
    <div class="row justify-content-center">
      <div class="alert alert-danger fade show" role="alert">
        <fmt:message bundle="${loc}" key="language.noRights"/>
      </div>
    </div>
  </div>
</c:if>
<c:if test="${sessionScope.role.roleName == 'admin'}">
  <form action="${pageContext.request.contextPath}/OnlineStore" method="post">
    <div class="p-3">
      <div class="row justify-content-center">
        <table class="table table-hover">
          <thead>
          <tr>
            <th scope="col"><fmt:message bundle="${loc}" key="language.orderId"/></th>
            <th scope="col"><fmt:message bundle="${loc}" key="language.fullName"/></th>
            <th scope="col"><fmt:message bundle="${loc}" key="language.mobilePhone"/></th>
            <th scope="col"><fmt:message bundle="${loc}" key="language.title"/></th>
            <th scope="col"><fmt:message bundle="${loc}" key="language.number"/></th>
            <th scope="col"><fmt:message bundle="${loc}" key="language.address"/></th>
            <th scope="col"><fmt:message bundle="${loc}" key="language.orderDate"/></th>
            <th scope="col"><fmt:message bundle="${loc}" key="language.deliveryDate"/></th>
            <th scope="col"><fmt:message bundle="${loc}" key="language.status"/></th>
            <th scope="col"><fmt:message bundle="${loc}" key="language.action"/></th>
          </tr>
          </thead>
          <c:forEach var="orderInformation" items="${ordersInformation}">
            <tr>
              <th width="50" scope="row">#<c:out value="${orderInformation.id}"/></th>
              <td width="150" class="text-primary">
                <c:set scope="request" var="informationNumberFlag" value="0"/>
                <c:forEach var="order" items="${ordersFromUser}">
                  <c:forEach var="user" items="${users}">
                    <c:forEach var="information" items="${userInformation}">
                      <c:if test="${user.id == order.userId && orderInformation.id == order.orderInformationId &&
                      information.id == user.userInformationId && informationNumberFlag == 0}">
                        <p><c:out value="${information.userSurname}"/>
                            <c:out value="${information.userName}"/>
                          <c:out value="${information.userPatronymicName}"/>
                        </p>
                        <c:set scope="request" var="informationNumberFlag" value="1"/>
                      </c:if>
                    </c:forEach>
                  </c:forEach>
                </c:forEach>
              </td>
              <td class="text-primary">
                <c:set scope="request" var="informationNumberFlag" value="0"/>
                <c:forEach var="order" items="${ordersFromUser}">
                  <c:forEach var="user" items="${users}">
                    <c:forEach var="information" items="${userInformation}">
                      <c:if test="${user.id == order.userId && orderInformation.id == order.orderInformationId &&
                        information.id == user.userInformationId && informationNumberFlag == 0}">
                        <p>+<c:out value="${information.userPhoneNumber}"/></p>
                        <c:set scope="request" var="informationNumberFlag" value="1"/>
                      </c:if>
                    </c:forEach>
                  </c:forEach>
                </c:forEach>
              </td>
              <td>
                <c:forEach var="order" items="${ordersFromUser}">
                  <c:forEach var="product" items="${products}">
                    <c:if test="${product.id == order.productId && orderInformation.id == order.orderInformationId}">
                            <p><c:out value="${product.productName}"/></p>
                    </c:if>
                  </c:forEach>
                </c:forEach>
              </td>
              <td>
                <c:forEach var="order" items="${ordersFromUser}">
                  <c:if test="${orderInformation.id == order.orderInformationId}">
                    <p><c:out value="${order.purchaseQuantity}"/></p>
                  </c:if>
                </c:forEach>
              </td>
              <td><c:out value="${orderInformation.deliveryAddress}"/></td>
              <td><c:out value="${orderInformation.orderDate}"/></td>
              <td><c:out value="${orderInformation.deliveryDate}"/></td>
              <td class="text-active"><c:out value="${orderInformation.orderStatus}"/></td>
              <td>
                <button onclick="location.href = '${pageContext.request.contextPath}/OnlineStore?command=completeUserOrder&orderInformationId=${orderInformation.id}'"
                        type="button" class="btn btn-outline-success">
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                       class="bi bi-check2" viewBox="0 0 16 16">
                    <path d="M13.854 3.646a.5.5 0 0 1 0 .708l-7 7a.5.5 0 0 1-.708 0l-3.5-3.5a.5.5 0 1 1 .708-.708L6.5 10.293l6.646-6.647a.5.5 0 0 1 .708 0z"></path>
                  </svg>
                </button>
              </td>
              <td>
                <button onclick="location.href = '${pageContext.request.contextPath}/OnlineStore?command=deleteOrderInformation&orderInformationId=${orderInformation.id}'"
                        class="btn btn-outline-danger" type="button">
                  <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20"
                       fill="currentColor"
                       class="bi bi-x-circle" viewBox="0 0 16 16">
                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"></path>
                    <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"></path>
                  </svg>
                </button>
              </td>
            </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </form>
</c:if>
</body>
<jsp:include page="fragments/footer.jsp"/>
</html>