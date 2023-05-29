<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Document</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>
    login page
    <form action="/login_process" method="POST" id="login-form">
        <input type="text" id="username" name="username" placeholder="user id"/>
        <br/>
        <input type="password" id="password" name="password" placeholder="user password"/>
        <br/>
        rememberMe : <input type="checkbox" id="rememberMe" name="rememberMe"/> <!-- rememberMe 기능 추가-->
        <button type="button" id="login" >login</button>
    </form>
</body>
<script type="text/javascript">
    $(document).ready(function() {
        $("#login").click(function() {
            let username = $("#username").val();

            $.ajax({
                url : "/checkUser",
                method : "GET",
                dataType : "json",
                data : {
                    username : username,
                },
                success : function(result){
                    if(result == false) {
                        $('#login-form').submit();
                    }else{
                        let check = confirm(
                            "Existing login users will be logged out. Do you want to continue?"
                        );
                        if(check) {
                            $('#login-form').submit();
                        }
                    }          
                }
            })
        })
    })
</script>
</html>