<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${message != null && status==1 && message != \"\" }">
        <div class="alertOk">
            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
            <c:out value="${message}" />
        </div>
    </c:when>
</c:choose>

<c:choose>
    <c:when test="${message != null && status==0 && message != \"\" }">
        <div class="alertFail">
            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
            <c:out value="${message}" />
        </div>
    </c:when>
</c:choose>