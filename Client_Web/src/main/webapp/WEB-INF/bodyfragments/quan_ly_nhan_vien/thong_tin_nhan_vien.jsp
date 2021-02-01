<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="resources/dist/css/qlttdn.css">
<script src="resources/model/chi_nhanh/ajax_chi_nhanh.js"></script>
<script src="resources/model/tai_khoan/ajax_tai_khoan.js"></script>
<script src="resources/model/upload_file/ajax_upload_file.js"></script>
<script src="resources/pages/quan_ly_thong_tin/ajax_thong_tin_tai_khoan.js"></script>
<!-- Modal -->
<div class="modal fade in" id="modal-remove">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Xác nhận thao tác</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12" id="text-confirm">
                        Bạn có chắc chắn xóa nhân viên này không ?
                    </div>
                </div>
            </div>
            <div class="modal-footer text-right">
                <button type="button" class="btn btn-danger mgr-10" data-dismiss="modal" id="confirm-yes">Đồng ý xóa</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Không xóa</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>


<div class="modal fade in" id="modal-change">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Xác nhận thao tác</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12" id="text-change">
                        Bạn có chắc chắn khóa nhân viên này không ?
                    </div>
                </div>
            </div>
            <div class="modal-footer text-right">
                <button type="button" class="btn btn-danger mgr-10" data-dismiss="modal" id="change-yes">Đồng ý khóa</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Không khóa</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>

<!-- Content Wrapper. Contains page content -->
<div class="content">
    <!-- Content Header (Page header) -->
    <div class="content-header">
        <div class="container">
            <div class="row mb-2">
                <div class="col-12 text-center">
                    <h2 class="m-0 text-dark">Quản lý thông tin nhân viên</h2>
                </div><!-- /.col -->
            </div><!-- /.row -->
            <div class="row justify-content-md-center mt-3">

                    <div class="form-group row">
                        <label class="col-md-4 col-form-label">Chi nhánh</label>
                        <div class="col-md-8">
                            <select class="form-control select2bs4 select2-hidden-accessible" id="select-1">
                                <option value=0>Tất cả các chi nhánh</option>
                                <option value=1>Chi nhánh 1</option>
                                <option value=2>Chi nhánh 2</option>
                                <option value=3>Chi nhánh 3</option>
                            </select>
                        </div>
                    </div>

            </div>
        </div><!-- /.container-fluid -->
    </div>
    <!-- /.content-header -->

    <!-- Main content -->
    <section class="content">
        <div class="container">
            <div class="card">
                <div class="card-header">
                    <div class="row justify-content-md-center m-3">
                        <div class="col-lg-6">
                            <div class="form-group row">
                                <label class=" col-md-4 col-form-label ">Thông tin nhân viên</label>
                                <div class=" col-md-6 input-group mb-3">
                                    <input type="text" class="form-control" placeholder="Nhập thông tin nhân viên" id="input-text-1">

                                </div>
                                <div class=" col-md-2">
                                    <button class="btn btn-secondary" id="btn-1" type="button"><i class="fas fa-search"></i></button>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6 text-right">
                            <strong>Tổng số nhân viên: <span id="text-1">03</span></strong>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <div class="row table-responsive">
                        <table class="table table-bordered table-hover text-center align-middle">
                            <thead class="color-main">
                            <tr>
                                <th >STT</th>
                                <th>Mã nhân viên</th>
                                <th>Tên nhân viên/Nhóm</th>
                                <th>Trạng thái</th>
                                <th>Chức năng</th>
                                <th>QR Code</th>
                            </tr>
                            </thead>
                            <tbody id="data-table">
                            <%--                            <tr class="color-opan">--%>
                            <%--                                <td></td>--%>
                            <%--                                <td><input type="text" class="form-control" placeholder="Nhập mã" id="input-text-2"></td>--%>
                            <%--                                <td><input type="text" class="form-control" placeholder="Nhập tên" id="input-text-3"></td>--%>
                            <%--                                <td><input type="text" class="form-control" placeholder="Nhập vị trí" id="input-text-4"></td>--%>
                            <%--                                <td><input type="text" class="form-control" placeholder="Mô tả" id="input-text-5"></td>--%>
                            <%--                                <td><input type="text" class="form-control" placeholder="Chưa đăng ký" id="input-text-6"></td>--%>
                            <%--                                <td><i class="fas fa-signal"></i></td>--%>
                            <%--                                <td><a href="#"><i class="fas fa-paperclip"></i> Cấu hình</a></td>--%>
                            <%--                            </tr>--%>
                            <%--                            <tr>--%>
                            <%--                                <td>1</td>--%>
                            <%--                                <td>TB001</td>--%>
                            <%--                                <td>Camera 01</td>--%>
                            <%--                                <td>Trước barrier</td>--%>
                            <%--                                <td>Nhóm thiết bị</td>--%>
                            <%--                                <td>Loại thiết bị</td>--%>
                            <%--                                <td>Nhận dạng biển số</td>--%>
                            <%--                                <td>Đã duyệt/ Đồng ý</td>--%>
                            <%--                                <td><i class="fas fa-signal text-primary"></i></td>--%>
                            <%--                                <td><i class="fas fa-paperclip"></i> Cấu hình</td>--%>
                            <%--                            </tr>--%>
                            <tr id="click-load-data">
                                <td colspan="10"><i class="fas fa-arrow-circle-down"></i></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="row mb-3">
                        <div class="col-12 text-center">
                            <h6 class="m-5 them-moi-tk">Thêm nhân viên  <i class="fa fa-plus-circle" aria-hidden="true"></i></h6>
                            <h4 class="m-0 text-dark">Thông tin chi tiết nhân viên</h4>
                        </div>
                    </div>

                    <div class="row mb-3">
                        <div class="col-md-6">
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Mã nhân viên</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="input-text-2" placeholder="Nhập mã nhân viên" disabled>
                                    <span class="invalid-feedback"></span>
                                </div>
                            </div>
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Họ và tên</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="input-text-3" placeholder="Nhập họ và tên">
                                    <span class="invalid-feedback"></span>
                                </div>
                            </div>
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">nhân viên (*)</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="input-text-4" placeholder="Nhập nhân viên" disabled>
                                    <span class="invalid-feedback"></span>
                                </div>
                            </div>
                            <div class="form-group row mb-3" id="show-mat-khau" style="display: none">
                                <label class="col-md-4 col-form-label">Mật khẩu (*)</label>
                                <div class="col-md-8">
                                    <input type="password" class="form-control" id="input-text-5"  placeholder="Nhập Mật khẩu" >
                                    <span class="invalid-feedback"></span>
                                </div>
                            </div>


                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Email (*)</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="input-text-6" placeholder="Nhập email" required="true" disabled>
                                    <span class="invalid-feedback"></span>
                                </div>
                            </div>


                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Số điện thoại (*)</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="input-text-7" placeholder="Nhập số điện thoại" required="true" >
                                    <span class="invalid-feedback"></span>
                                </div>
                            </div>


                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Địa chỉ</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" id="input-text-8" placeholder="Nhập địa chỉ" required="true">
                                    <span class="invalid-feedback"></span>
                                </div>
                            </div>

                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Giới tính</label>
                                <div class="col-md-8" id="radio-1">
                                    <input class="col-mb-4" type="radio" name="gender" value="1" id="gTNam">
                                    <label  class="mr-5 mb-0">Nam</label>
                                    <input class="col-mb-4" type="radio" name="gender" value="0" id="gTNu">
                                    <label class="mb-0">Nữ</label>
                                    <span class="invalid-feedback"></span>
                                </div>
                            </div>

                            <div class="form-group row mb-3"  >
                                <label class="col-md-4 col-form-label">Qr Code</label>
                                <div class="col-md-8" id="qrcode"></div>
                            </div>
                        </div>


                        <div class="col-md-6">
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Nhóm nhân viên</label>
                                <div class="col-md-8">
                                    <select class="form-control"  id="select-3">
                                        <option value="">Guest</option>
                                        <option value="">Administrator</option>
                                        <option value="">User</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Ngày sinh</label>
                                <div class="col-md-8">
                                    <input type="date" class="form-control" id="input-date-1" value="2016-05-26" placeholder="Nhập ngày sinh">
                                    <span class="invalid-feedback"></span>
                                </div>
                            </div>



                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Thời gian khởi tạo</label>
                                <div class="col-md-8">
                                    <input type="date" class="form-control" id="input-date-2" placeholder="Nhập thời gian khởi tạo">
                                    <span class="invalid-feedback"></span>
                                </div>
                            </div>

                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Thời gian kích hoạt</label>
                                <div class="col-md-8">
                                    <input type="date" class="form-control"  id="input-date-3" placeholder="Nhập thời gian kích hoạt">
                                    <span class="invalid-feedback"></span>
                                </div>
                            </div>

                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Thời gian hết  hạn</label>
                                <div class="col-md-8">
                                    <input type="date" class="form-control"  id="input-date-4" placeholder="Nhập thời gian hết hạn">
                                    <span class="invalid-feedback"></span>
                                </div>
                            </div>

                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Trạng thái</label>
                                <div class="col-md-8">
                                    <input class="col-mb-4" type="radio" name="status" value="0" id="kichHoat">
                                    <label  class="mr-5 mb-0">Kích hoạt</label>
                                    <input class="col-mb-4" type="radio" name="status" value="1" id="khoa">
                                    <label class="mb-0">Khóa</label>
                                    <span class="invalid-feedback"></span>
                                </div>
                            </div>

                            <div class="form-group row mb-3">
                                <label class="col-md-4 col-form-label">Ảnh đại diện</label>
                                <div class="col-md-8">
                                    <form class="custom-file" id="form-file">
                                        <input type="file" class="custom-file-input form-control" id="customFile-1" name="files">
                                        <label class="custom-file-label">Ảnh dại diện</label>

                                        <span class="invalid-feedback"></span>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row text-center">
                        <button type="button" class="btn btn-success mr-1 show-them-moi" id="btn-3" style="display: none">Thêm mới</button>
                        <button type="button" class="btn btn-secondary mr-1" id="btn-4" style="display: none">Cập nhập</button>
                        <button type="button" class="btn btn-secondary mr-1 them-moi" id="btn-2">Làm mới</button>
                        <%--                        <button type="button" class="btn btn-secondary m-1" id="btn-5" data-toggle="modal" data-target="#modal-remove">Xóa</button>--%>
                    </div>
                </div>
            </div>
        </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->
</div>
<!-- /.content-wrapper -->
