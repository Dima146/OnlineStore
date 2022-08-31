<%--
  Created by IntelliJ IDEA.
  User: Dzmitry
  Date: 04.08.2022
  Time: 00:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <jsp:include page="fragments/header.jsp"/>
    <title>Catalog | Online store</title>
</head>


<body>
<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="localization.language" var="loc"/>
<fmt:setBundle basename="information" var="info"/>

<div class="container">
    <c:if test="${param.message == 'error'}">
        <div class="alert alert-danger fade show" role="alert">
            <fmt:message bundle="${loc}" key="language.productAlreadyInTheBasket"/>
        </div>
    </c:if>
    <c:if test="${param.message == 'ok'}">
        <div class="alert alert-success fade show" role="alert">
            <fmt:message bundle="${loc}" key="language.productAddedToBasket"/>
        </div>
    </c:if>
    <c:forEach var="product" items="${products}">
        <div class="my-lg-4">
            <div class="col-6"></div>
                <div class="card text-center">
                    <div class="card-header">
                        <h5><c:out value="${product.productName}"/>
                            <small class="text-muted"> #<c:out value="${product.id}"/></small>
                            <c:if test="${sessionScope.role.roleName == 'admin'}">
                                <a href="${pageContext.request.contextPath}/OnlineStore?command=editProduct&productId=${product.id}">
                                    <fmt:message bundle="${loc}" key="language.edit"/>
                                </a>
                            </c:if>
                        </h5>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-4">
                                <img src="static/images/<c:out value="${product.productPhotoReference}"/>" class="img-fluid" width="150" height="150">
                            </div>
                            <div class="col-md-8">
                                <p><fmt:message bundle="${loc}" key="language.description"/>: <c:out value="${product.productDescription}"/></p>
                                <div class="container">
                                    <div class="row">
                                        <div class="col-sm text-left">
                                            <c:if test="${product.productStatus == true}">
                                                <p class="text-success">
                                                    <fmt:message bundle="${loc}" key="language.available"/></p>
                                            </c:if>
                                            <c:if test="${product.productStatus == false}">
                                                <p class="text-danger">
                                                    <fmt:message bundle="${loc}" key="language.notAvailable"/></p>
                                            </c:if>
                                        </div>
                                        <div class="col-sm text-right">
                                            <p><fmt:message bundle="${loc}" key="language.price"/>: <c:out value="${product.productPrice}"/> <fmt:message bundle="${info}" key="information.price"/></p>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${sessionScope.user != null}">
                                    <c:if test="${product.productStatus == true}">

                                    <form action="${pageContext.request.contextPath}/OnlineStore?command=addInBasket" method="post">

                                        <div class="btn-group" role="group">
                                            <input type="number" class="form-control col-md-4" step="1" min="1" max="100" id="num_count" name="quantity" value="1" title="Qty">

                                            <button type="submit" class="btn btn-outline-primary" name="productId" value="${product.id}">
                                                <fmt:message bundle="${loc}" key="language.addToBasket"/></button>
                                        </div>
                                    </form>
                                    </c:if>
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>
        </div>
    </c:forEach>
</div>
</body>
<jsp:include page="fragments/footer.jsp"/>
</html>