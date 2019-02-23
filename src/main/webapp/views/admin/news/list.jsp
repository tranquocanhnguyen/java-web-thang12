<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="deleteURL" value="/api-admin-news"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<div class="main-content">
    <form action='<c:url value="/admin-news"/>' id="formSubmit" method="get">
        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
                        chủ</a></li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="widget-box table-filter">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <div class="form-horizontal">
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right">Tên bài viết</label>
                                            <div class="col-sm-9">
                                                <input type="text" id="title" name="title" class="form-control" value="${model.title}"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right">loại bài viết</label>
                                            <div class="col-sm-9">
                                                <select class="form-control" id="categoryCode" name="categoryCode">
                                                    <c:if test="${empty model.categoryCode}">
                                                        <option value="">Chọn loại bài viết</option>
                                                        <c:forEach var="item" items="${categories}">
                                                            <option value="${item.code}">${item.name}</option>
                                                        </c:forEach>
                                                    </c:if>
                                                    <c:if test="${not empty model.categoryCode}">
                                                        <option value="">Chọn loại bài viết</option>
                                                        <c:forEach var="item" items="${categories}">
                                                            <option value="${item.code}" <c:if test="${item.code == model.categoryCode}">selected="selected"</c:if>>
                                                                    ${item.name}
                                                            </option>
                                                        </c:forEach>
                                                    </c:if>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-sm-3 control-label no-padding-right"></label>
                                            <div class="col-sm-9">
                                                <button id="btnSearch" type="button" class="btn btn-sm btn-success">
                                                    Tìm kiếm
                                                    <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <a flag="info"
                                           class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                           data-toggle="tooltip"
                                           title='Thêm bài viết' href='<c:url value="/admin-news?type=edit"/>'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                                        </a>
                                        <button id="btnDelete" type="button" disabled
                                                class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                data-toggle="tooltip" title='Xóa bài viết'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:if test="${not empty message}">
                            <div class="alert alert-${alert}">
                                    ${message}
                            </div>
                        </c:if>
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <td><input type="checkbox" class="check-box-element" id="checkAll"></td>
                                <th>Title</th>
                                <th>ShortDescription</th>
                                <th>Thao tác</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="item" items="${model.listResults }">
                                <tr>
                                    <td><input type="checkbox" class="check-box-element" id="checkbox_${item.id}"
                                               value="${item.id}"></td>
                                    <td>${item.title }</td>
                                    <td>${item.shortDescription }</td>
                                    <td>
                                        <c:url var="editURL" value="/admin-news">
                                            <c:param name="type" value="edit"/>
                                            <c:param name="id" value="${item.id}"/>
                                        </c:url>
                                        <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                           title="Cập nhật bài viết" href='${editURL}'><i class="fa fa-pencil-square-o"
                                                                                          aria-hidden="true"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <ul class="pagination" id="pagination"></ul>
                        <input type="hidden" value="" id="page" name="page">
                        <input type="hidden" value="" id="maxPageItem" name="maxPageItem">
                        <input type="hidden" value="" id="sortName" name="sortName">
                        <input type="hidden" value="" id="sortBy" name="sortBy">
                        <input type="hidden" value="" id="type" name="type">
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<!-- /.main-content -->
<script type="text/javascript">
    var totalPages =
    ${model.totalPage}
    var currentpage =
    ${model.page}
    var visiblePages =
    ${model.maxPageItem}
    var limit = 2;
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 5,
            startPage: currentpage,
            onPageClick: function (event, page) {
                if (page != currentpage) {
                    $('#sortName').val('title');
                    $('#sortBy').val('desc');
                    $('#maxPageItem').val(limit);
                    $('#page').val(page);
                    $('#type').val("list");
                    $('#formSubmit').submit();
                }
            }
        });
    });
    $('#btnDelete').click(function (e) {
        e.preventDefault();
        var dataArray = $('tbody :checkbox:checked').map(function () {
            return $(this).val();
        }).get();
        var data = {};
        data["ids"] = dataArray;
        $.ajax({
            url: "${deleteURL}",
            method: "delete",
            contentType: "application/json",
            data: JSON.stringify(data),
            dataType: "json",
            success: function (result) {
                window.location.href =
               '<c:url value="/admin-news?type=list&message=delete_success&alert=success&page=1&maxPageItem=2"/>';
            },
            error: function (error) {
                window.location.href =
                '<c:url value="/admin-news?type=list&message=delete_erroe&alert=danger&page=1&maxPageItem=2" />';
            }
        });
    });
    $('#btnSearch').click(function () {
        $('#maxPageItem').val(limit);
        $('#page').val(1);
        $('#type').val("list");
        $('#formSubmit').submit();
    });
</script>
</body>
</html>