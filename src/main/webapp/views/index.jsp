<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="../WEB-INF/tableTags.tld" prefix="table"%>
<%@ taglib uri="../WEB-INF/errorTag.tld" prefix="erro"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet">
	<link rel="icon" type="image/png" href="https://cdn-icons-png.flaticon.com/512/10384/10384161.png">
	
<style>
body {
    color: #404E67;
    background: #F5F7FA;
    font-family: 'Open Sans', sans-serif;
}
.table-wrapper {
    width: 900px;
    margin: 30px auto;
    background: #fff;
    padding: 20px;	
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}
.table-title {
    padding-bottom: 10px;
    margin: 0 0 10px;
}
.table-title h2 {
    margin: 6px 0 0;
    font-size: 22px;
}
.table-title .add-new {
    float: right;
    height: 30px;
    font-weight: bold;
    font-size: 12px;
    text-shadow: none;
    min-width: 100px;
    border-radius: 50px;
    line-height: 13px;
}
.table-title .add-new i {
    margin-right: 4px;
}
table.table {
    table-layout: fixed;
}
table.table tr th, table.table tr td {
    border-color: #e9e9e9;
}
table.table th i {
    font-size: 13px;
    margin: 0 5px;
    cursor: pointer;
}
table.table th:last-child {
    width: 100px;
}
table.table td a {
    cursor: pointer;
    display: inline-block;
    margin: 0 5px;
    min-width: 24px;
}    
table.table td a.add {
    color: #27C46B;
}
table.table td a.edit {
    color: #FFC107;
}
table.table td a.delete {
    color: #E34724;
}
table.table td i {
    font-size: 19px;
}
table.table td a.add i {
    font-size: 24px;
    margin-right: -1px;
    position: relative;
    top: 3px;
}    
table.table .form-control {
    height: 32px;
    line-height: 32px;
    box-shadow: none;
    border-radius: 2px;
}
table.table .form-control.error {
    border-color: #f50000;
}
table.table td .add {
    display: none;
}
.container-custom {
	margin-left: 70px;
}

</style>	
<title>vcRiquinho</title>
</head>
<body>
	<jsp:include page="../component/navbar.jsp" />

	<div class="container mt-5">
	
		<erro:message attribute="semPermissao"></erro:message>
		
		<div class="row d-flex justify-content-center align-items-center h-90">
			<div class="col-12 col-md-12 col-lg-10 col-xl-7 mt-2 container-custom">
				<h1>Seja bem vindo ao vcRiquinho</h1>
			</div>
		</div>
	</div>
	

	<div class="container mt-5">
		
		
		 <div class="table-responsive">
	       <div class="table-wrapper">
	            <div class="table-title">
	                <div class="row">
	                    <div class="col-sm-8 mb-2"><h2>Produtos <b>Detalhes</b></h2></div>
	                </div>
	                
					<table:produto/>
					
				</div>
			</div>
		</div>
	</div>
	
	
</body>
</html>