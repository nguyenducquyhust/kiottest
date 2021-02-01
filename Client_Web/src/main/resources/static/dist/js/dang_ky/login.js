
function ajaxLogin(data){
    return ajaxPost(  "v1/public/user/login",data);
}

$(document).ready(function () {

    $('#btn-login').click(function (e) {
        e.preventDefault();
        console.log("login");
        onSubmit(e);
    });

    $('#btn-login-fb').click(function (e) {
        e.preventDefault();
        let urlFb=urlApi+"login-facebook";
        document.location.assign(urlFb);
        alert("Tính năng này chưa được hỗ trợ");
    });

    $( "#btn-quen-mat-khau" ).click(function(e) {
        e.preventDefault();
        console.log("quen mat khau");
        location.href=  "tim-tai-khoan";
    });


    $('#btn-login-gg').click(function(e){
        e.preventDefault();
        let urlGg=urlApi+"login-google";
        document.location.assign(urlGg);
        alert("Tính năng này chưa được hỗ trợ");
    })


    $( "#btn-dang-ky" ).click(function(e) {
        e.preventDefault();
        console.log("dang ky");
        location.href=  "dang-ky";
    });
});



function onSubmit(event){
    var username=$('#username').val();
    var password=$('#password').val();
    console.log(username);
    console.log(password);
    if(username===null || password===null){
        alert("vui lòng nhập đầy đủ username và password!");
        return;
    }
    else if(password.length<8){
        alert("password nhập thiếu kí tự");
        return;
    }

    let LoginForm={
        username:username,
        password:password
    }
    console.log(LoginForm);

    ajaxLogin(LoginForm).then((rs)=>{
        console.log(rs);
        if(rs.message=="login success"){
            // location.replace(urlApi+'organization');
            window.sessionStorage.setItem("token",rs.data);
            location.href = "danh-sach-hang-hoa";
        }
        else {
            alert("Đăng nhập thất bại");
        }
    }).catch(er=>{
        console.log(er);
        $('.login-box-msg').html('Username hoặc Password không đúng, vui lòng kiểm tra lại');
        $('.login-box-msg').css('color','red');
    })

}




function errMess(jqXHR, textStatus, errorThrown) {
    console.log('jqXHR:');
    console.log(jqXHR);
    console.log('textStatus:');
    console.log(textStatus);
    console.log('errorThrown:');
    console.log(errorThrown);
}


