<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="userURL" value="/quan-tri/nguoi-dung"/>
<c:url var="userEdit" value="/quan-tri/nguoi-dung/chinh-sua"/>
<c:url var="userAPI" value="/api/user"/>
<html>
<head>
<title>Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
	<div class="main-content-inner">
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>

			<ul class="breadcrumb">
				<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
				</li>

				<li><a href="#">Forms</a></li>
				<li class="active">Form Elements</li>
			</ul>
			<!-- /.breadcrumb -->
		</div>
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
				<c:if test="${not empty message}">
						<div class="alert alert-${alert}">
  							${message}
						</div>
					</c:if>	
					<form:form class="form-horizontal" role="form" id ="formSubmit" modelAttribute="model">
						<div class="form-group">
							  <label for="categoryCode" class="col-sm-3 control-label no-padding-right">Quyền truy cập:</label>
							  <div class="col-sm-9">
							  	 <form:select path="roleCode" id ="roleCode">
							  	 <form:option value="" label="--Chon Role--"/>
							  	 <form:options items="${role}"/>
							  	 </form:select> 
							  </div>
						</div>
						<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Họ và tên</label>
								<div class="col-sm-9">
									<form:input path="fullName" id="fullName" cssClass="col-xs-10 col-sm-5"/>
								</div>
						</div>
						<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Tên đăng nhập</label>
								<div class="col-sm-9">
									<form:input path="userName" id="userName" cssClass="col-xs-10 col-sm-5"/>
								</div>
						</div>
						<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Mật khẩu</label>
								<div class="col-sm-9">
									<form:input path="password_convert" id="password_convert" cssClass="col-xs-10 col-sm-5"/>
								</div>
						</div>
						<div class="form-group">
							  <label for="categoryCode" class="col-sm-3 control-label no-padding-right">Trạng thái:</label>
						 <div class="col-sm-9">
							  	 <form:select path="status" id ="status">
							  	 <form:options items="${st}"/>
						  </form:select>
						  </div>
						  </div>
						<form:hidden path="id" id="userId"/>
						<div class="clearfix form-actions">
							<div class="col-md-offset-3 col-md-9">
											<c:if test="${ not empty model.id}">
												<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
													<i class="ace-icon fa fa-check bigger-110"></i>
													Cập nhật người dùng
												</button>
											</c:if>  
											<c:if test="${empty model.id}">
												<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
													<i class="ace-icon fa fa-check bigger-110"></i>
													Thêm người dùng
												</button>
											</c:if>  

											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset">
												<i class="ace-icon fa fa-undo bigger-110"></i>
												Hủy
											</button>
							</div>		
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>	

<script>
$('#btnAddOrUpdateNew').click(function (e) {
    e.preventDefault();
    var data = {};
    var formData = $('#formSubmit').serializeArray();
    $.each(formData, function (i, v) {
        data[""+v.name+""] = v.value;
    });
    var id = $('#userId').val();
    if (id == "") {
    	addUser(data);
    } else {
    	updateUser(data);
    }
});
function addUser(data) {
	$.ajax({
        url: '${userAPI}',
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (result) {
        	window.location.href = "${userEdit}?id="+result.id+"&message=insert_success";
        },
        error: function (error) {
        	window.location.href = "${userURL}?page=1&limit=2&message=error_system";
        }
    });
}

function updateUser(data) {
	$.ajax({
        url: '${userAPI}',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (result) {
        	window.location.href = "${userEdit}?id="+result.id+"&message=update_success";
        },
        error: function (error) {
        	window.location.href = "${userEdit}?id="+result.id+"&message=error_system";
        }
    });
}
	
	
</script>
</body>
</html>