<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>

</head>
<body>
<div class="main-content">
	<div class="main-content-inner">
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
				
						<div class="form-group" style="width: 100%;padding-left: 10px;padding-right: 10px;margin-top: 5px;">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Server Url:</label>
								<div class="col-sm-9">
									<input name="fullName" style="margin-top:5px; width: 100% ; height: 40px; padding-left: 10px;padding-right: 10px;border-radius: 5px;border-width: 1px;border-color: #707070" id="fullName" cssClass="col-xs-10 col-sm-5"/>
								</div>
						</div>
						<div class="form-group" style="width: 100%;padding-left: 10px;padding-right: 10px;margin-top: 5px;">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">Các vai trò: </label>
								<div class="col-sm-9">
									<input name="fullName" style="margin-top:5px; width: 100% ; height: 40px; padding-left: 10px;padding-right: 10px;border-radius: 5px;border-width: 1px;border-color: #707070" id="fullName" cssClass="col-xs-10 col-sm-5"/>
								</div>
						</div>
						<div class="form-group" style="width: 100%;padding-left: 10px;padding-right: 10px;margin-top: 5px;">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">jrxml:</label>
								<div class="col-sm-9">
									<input name="fullName" value="${tenfile}" style="margin-top:5px; width: 100% ; height: 40px; padding-left: 10px;padding-right: 10px;border-radius: 5px;border-width: 1px;border-color: #707070" id="fullName" cssClass="col-xs-10 col-sm-5"/>
								</div>
						</div>
						<div class="form-group" style="width: 100%;padding-left: 10px;padding-right: 10px;margin-top: 5px;">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">report-name:</label>
								<div class="col-sm-9">
									<input name="fullName" style="margin-top:5px; width: 100% ; height: 40px; padding-left: 10px;padding-right: 10px;border-radius: 5px;border-width: 1px;border-color: #707070" id="fullName" cssClass="col-xs-10 col-sm-5"/>
								</div>
						</div>
						
						<div class="form-group" style="width: 100%;padding-left: 10px;padding-right: 10px;margin-top: 5px;">
								<label class="col-sm-3 control-label no-padding-right" for="form-field-1">report-name:</label>
								<div class="col-sm-9">
									<input name="fullName" style="margin-top:5px; height: 40px; padding-left: 10px;padding-right: 10px;border-radius: 5px;border-width: 1px;border-color: #707070" id="fullName" type="file" />
								</div>
						</div>
		
						
						<div class="clearfix form-actions" style="margin:  20px">
							<div class="col-md-offset-3 col-md-9">
											
												<button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
													<i class="ace-icon fa fa-check bigger-110"></i>
													Cập nhật người dùng
												</button>

											&nbsp; &nbsp; &nbsp;
											<button class="btn" type="reset">
												<i class="ace-icon fa fa-undo bigger-110"></i>
												Hủy
											</button>
							</div>		
						</div>
					
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
   
   
});

	
</script>
</body>
</html>