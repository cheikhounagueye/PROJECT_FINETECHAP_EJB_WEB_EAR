<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Form</title>
<style>
    body {
        font-family: 'Arial', sans-serif;
        background: #f7f7f7;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin: 0;
    }

    .container {
        background: #fff;
        padding: 2rem;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        max-width: 400px;
        width: 100%;
    }

    h1 {
        margin-bottom: 2rem;
        color: #333;
        text-align: center;
    }

    .form-group {
        margin-bottom: 1.5rem;
    }

    .form-style {
        width: calc(100% - 40px);
        padding: 10px 20px;
        font-size: 1rem;
        border: 1px solid #ddd;
        border-radius: 5px;
        outline: none;
        transition: border-color 0.3s;
    }

    .form-style:focus {
        border-color: #007bff;
    }

    .input-icon {
        position: absolute;
        margin-left: -35px;
        margin-top: 15px;
        color: #ccc;
    }

    input[type="submit"] {
        width: 100%;
        padding: 10px 0;
        background: #007bff;
        border: none;
        border-radius: 5px;
        color: #fff;
        font-size: 1rem;
        cursor: pointer;
        transition: background 0.3s;
    }

    input[type="submit"]:hover {
        background: #0056b3;
    }

    .card-3d-wrap {
        position: relative;
        width: 100%;
        margin-top: 50px;
    }

    .card-3d-wrapper {
        width: 100%;
        position: relative;
        -webkit-transform-style: preserve-3d;
        transform-style: preserve-3d;
    }

    .card-front {
        position: absolute;
        width: 100%;
        backface-visibility: hidden;
        background: #fff;
        border-radius: 10px;
        padding: 2rem;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .center-wrap {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
</style>
</head>
<body>
    <div class="container">
        <h1>Login</h1>
        <form action="login" method="post">
            <div class="form-group">
                <input type="email" name="logemail" class="form-style" placeholder="Your Email" id="logemail" autocomplete="off">
                <i class="input-icon uil uil-at"></i>
            </div>
            <div class="form-group">
                <input type="password" name="logpass" class="form-style" placeholder="Your Password" id="logpass" autocomplete="off">
                <i class="input-icon uil uil-lock-alt"></i>
            </div>
            <input type="submit" value="Connexion" name="action">
        </form>
    </div>
</body>
</html>
