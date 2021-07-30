<%-- 
    Document   : connexion
    Created on : 2 mai 2021, 14:48:14
    Author     : Généreux AKOTENOU
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>IFRI - FAQ | Connexion</title>
    <style>
        input{
            padding: 0.5em;
            width: 95%;
            margin-top: 0.2em;
        }
        label{

        }
    </style>
</head>
<body>
    <div style="position: relative; left: -12em; width: 20em; margin: auto; height: auto; background-color: #ddd; padding: 0.5em; text-align: center; margin-top: 10em;">
        <p>SITE EN MEINTENANCE</p>
        <br><br>
        <label for="">login</label><br>
        <input type="text" name="username"/>
        <br>
        <label for="">password</label><br>
        <input type="text" /><br><br><br><br>
        <input type="submit" value="Se connecter"><br><br>
        <span> Vous n'avez pas un compte ? <a href="">creez en</a> </span>
    </div>
    <br>
    <div style=" float: right; width: 20em; height: auto; background-color: #ddd; padding: 0.5em; text-align: center; position: relative; top: -22.4em; left: -17em;">
        <p>SITE EN MEINTENANCE</p>
        <br>
        <label for="">username</label><br>
        <input type="text" />
        <br>
        <label for="">email</label><br>
        <input type="text" />
        <br>
        <label for="">password</label><br>
        <input type="text" /><br><br><br>
        <input type="submit" value="Se connecter"><br><br>
        <span> Vous n'avez pas un compte ? <a href="">creez en</a> </span>
    </div>
</body>
</html>