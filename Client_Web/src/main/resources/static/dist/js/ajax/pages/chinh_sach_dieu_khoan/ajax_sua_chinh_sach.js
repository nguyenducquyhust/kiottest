
var url_api = "http://localhost:8181/api/";
var btnCapNhap = $("#btn-cap-nhap");
$(function () {
    getdk();
    capNhap();
})



async function ajaxGet(urls) {
    let rs = null;
    await $.ajax({
        type: 'GET',
        headers: {
            "Authorization": ""
        },
        dataType: "json",
        url : url_api + urls ,
        timeout: 30000,
        success: function (result) {
            rs = result;
        }
    })
    console.log("1");
    console.log(rs );
    return rs;
}


function getdk()
{
    thongTinDieuKhoan().then(rs => {
        let dieukhoan = rs.data.noiDung;
        console.log($(".nicEdit-main"));
        $(".nicEdit-main").html(dieukhoan);
        $(".nicEdit-main").addClass("intro");
    })
}



async function thongTinDieuKhoan() {
    return ajaxGet(`v1/public/chinh-sach/find-by-id?id=1`);
}

function capNhap() {
    btnCapNhap.click(function () {
       var noiDung =  $(".nicEdit-main").html();
       let dk = {
           id: 1,
           noiDung: noiDung,
           xoa: false
       }
       ajaxPut(`v1/public/chinh-sach/update`,dk,1).then(rs=>{
           if (rs.message==="updated") {
               alert("Sửa chính sách thành công", TIME_ALTER);
           }
           else {
               alert("Server error - Sửa chính sách thất bại", TIME_ALTER);
           }
       });
    })
}