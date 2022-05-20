function pwSame() {
    var put_pwd = document.getElementById('signup_pwd');
    var checkPwd = document.getElementById('signup_pwd_check');
    var pwdSame = document.getElementById('pwSame');

    if(put_pwd.value==checkPwd.value) {
        pwdSame.innerHTML='비밀번호가 일치합니다.';
        pwdSame.style.color='blue';
    }
    else if(put_pwd.value=='') {
        pwdSame.innerHTML='';
    }
    else {
        pwdSame.innerHTML='비밀번호가 일치하지 않습니다.';
        pwdSame.style.color='red';
    }
}