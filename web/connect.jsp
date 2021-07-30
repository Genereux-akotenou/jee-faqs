<div id="connetETregister">
    
    <div id="connectBox" class="userEnterBox ttls1">
        <h2>Connexion</h2>
        <form action="faqServlet" method="POST">
            <div class="">
                <span>Nom d'utilisateur</span>
                <input type="text" name="login">
            </div>
            <div class="">
                <span>Mot de passe   </span>
                <input type="password" name="password">
            </div>
            <div class="remember"><br>
                <input id="check-box" type="checkbox" name="remeberMe">
                <label class="tssl" for="#check-box">Me le rappeler</label>
            </div>
            <div class="inputBox">
                <label><input type="submit"  value ="Se connecter" name="connexion">
            </div>
            <div class="inputBox">
                <p>Vous n'avez pas de compte ?<a class="linkS" onclick="displayRegister()">Inscrivez-vous</a></p>
            </div>
        </form>
    </div>
    
    <div id="regsiterBox" class="userEnterBox ttls">
        <h2>Inscription</h2>
        <form action="faqServlet" method="POST">
            <div class="">
                <span>Nom d'utilisateur</span>
                <input type="text" name="login">
            </div>
            <div class="">
                <span>Email   </span>
                <input type="email" name="mail">
            </div>
            <div class="">
                <span>Mot de passe   </span>
                <input type="password" name="password-check1">
            </div>
            <div class="">
                <span>Confirmer le mot de passe   </span>
                <input type="password" name="password-check2">
            </div>
            <div class="remember"><br>
                <input value="save" id="check-box" type="checkbox" name="remeberMe">
                <label class="tssl" for="check-box">Me le rappeler</label>
            </div>
            <div class="inputBox">
                <label><input type="submit"  value ="S'inscrire" name="inscription">
            </div>
            <div class="inputBox">
                <p>Vous n'avez pas de compte ?<a class="linkS" onclick="displayConnect()">Inscrivez-vous</a></p>
            </div>
            <br>
        </form>
    </div>
</div>