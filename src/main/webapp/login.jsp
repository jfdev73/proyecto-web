<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="layout/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1 class="text-center my-3">${title}</h1>

<div class="container d-flex justify-content-center">
	<form action="/web-session/login" method="post" >
		<div class="row my-2">
			<label for="username" class="form-label">Username</label>
			<div>
				<input type="text" name="username" id="username"
					class="form-control">
			</div>
		</div>

		<div class="row my-2">
			<label for="password" class="form-label">Password</label>
			<div>
				<input type="password" name="password" id="password"
					class="form-control">
			</div>
		</div>

		<div class="text-center my-3">
			<input type="submit" value="Login" class="btn btn-primary w-25 p-1">

		</div>
	</form>
</div>
<jsp:include page="layout/footer.jsp" />