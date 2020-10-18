document.querySelector('.img__btn').addEventListener('click', function() {
    document.querySelector('.cont').classList.toggle('s--signup');
});

function login_validate() {
    let phone_number = document.getElementById("login_phone_number").value;
    let password = document.getElementById("login_user_password").value;
    let is_phone_number_valid = /^1[3456789]\d{9}$/.test(phone_number);
    let password_length = password.length;

    if (is_phone_number_valid && password_length >= 6 && password_length <= 20) {
        return true;
    }

    let message_text_div = $("#login_message_text")[0];
    if (!is_phone_number_valid) {
        message_text_div.innerText = "输入的手机号不合法";
    } else if (password_length < 6 || password_length > 20) {
        message_text_div.innerText = "密码长度应为6-20位";
    }
    message_text_div.style = "";
    return false;
}

function register_validate() {
    let phone_number = document.getElementById("register_phone_number").value;
    let password = document.getElementById("register_user_password").value;
    let password_confirm = document.getElementById("register_user_password_confirm").value;
    let password_length = password.length;

    let is_phone_number_valid = /^1[3456789]\d{9}$/.test(phone_number);
    let password_same = (password === password_confirm);

    if (password_same && is_phone_number_valid && password_length >= 6) {
        return true;
    }

    let message_text_div = $("#register_message_text")[0];
    if (!is_phone_number_valid) {
        message_text_div.innerText = "输入的手机号不合法";
    } else if (!password_same) {
        message_text_div.innerText = "两次输入的密码不一致";
    } else if (password_length < 6 || password_length > 20) {
        message_text_div.innerText = "密码长度应为6-20位";
    }
    message_text_div.style = "";
    return false;
}