<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>BisMaps</title>
	<script type="text/javascript">
		$(document).ready(function () {
			$('#ilce').submit(function (e) {
				e.preventDefault();
				
				$.ajax('/maps/ilceler/kaydet', {
					data: $(this).serialize(),
					type: 'json',
					success: function () { alert('Kaydedildi'); },
					error: function () { alert('Hata'); },
					complete: function () { }
				});
			});
		});		
	</script>
</head>
<body>
<form id="ilce">
<label>Bölge :</label>
<select name="bolgeId">
<c:forEach items="${bolgeler}" var="bolge">
	<option value="${bolge.id}">${bolge.ad}</option>
</c:forEach>
</select>
<label> İlçe Ad :</label>
<input type="text" name="ilceAdi">
<input type="submit" value="Kaydet">
</form>

</body>
</html>
