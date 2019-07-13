<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name</label>
            <div class="col-sm-6">
                <input class="form-control ${(usernameError??)?string('is-invalid','')}" type="text" name="username" value="<#if user??>${user.username}</#if>"/>
                <#if usernameError??><div class="invalid-feedback">${usernameError}</div></#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-6">
                <input class="form-control ${(passwordError??)?string('is-invalid','')}" type="password" name="password"/>
                <#if passwordError??><div class="invalid-feedback">${passwordError}</div></#if>
            </div>
        </div>

        <#if isRegisterForm>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Retype Password</label>
            <div class="col-sm-6">
                <input class="form-control ${(password2Error??)?string('is-invalid','')}" type="password" name="password2"/>
                <#if password2Error??><div class="invalid-feedback">${password2Error}</div></#if>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-6">
                <input class="form-control ${(emailError??)?string('is-invalid','')}" type="email" name="email" placeholder="some@some.com" value="<#if user??>${user.email}</#if>"/>
                <#if emailError??><div class="invalid-feedback">${emailError}</div></#if>
            </div>
        </div>
        </#if>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <button type="submit" class="btn btn-primary"><#if isRegisterForm>Register<#else>Sign In</#if></button>
        <#if !isRegisterForm>
            <a class="btn btn-secondary" href="/registration" role="button">Register</a>
        </#if>

    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post" class="ml-2">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-secondary">Sign Out</button>
    </form>
</#macro>