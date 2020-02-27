<%--
    Document   : login
    Created on : Feb 26, 2020, 6:35:44 PM
    Author     : makis
--%>


<div style="text-align: center;">
    <form action="" method="post">
        <fieldset id="fieldsetcss">
            <legend id="legendcss"> Please sign in</legend>
            <div class="form-inline">
                <label>Username</label>
                <input type="text" name="username" class="form-control" placeholder="Enter Username">
            </div>
            <br>
            <div class="form-inline">
                <label>Password</label>
                <input type="password" name="password" class="form-control" placeholder="Enter Password">
            </div>
            <br>
            <button type="submit" name="action" value="signin" class="btn">Sign in</button>
            <button type="reset" class="btn">Cancel</button>
        </fieldset>
    </form>
</div>