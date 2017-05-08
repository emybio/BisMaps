<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>BisMaps</title>
</head>
<body>
<div>
<label>Mahalle / Köy :</label>
<select>
<c:forEach items="${bolgeler}" var="bolge">
	<option value="${bolge.id}">${bolge.ad}</option>
</c:forEach>
</select>
<label>Cadde / Sokak Ad :</label>
<input>
<label>Yüz Ölçüm :</label>
<input>
<input type="BUTTON" value="Kaydet">
</div>

</body>
</html>
