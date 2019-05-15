<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name</label>
            <div class="col-sm-6">
                <input class="form-control" type="text" name="username"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-6">
                <input class="form-control" type="password" name="password"/>
            </div>
        </div>

        <#if isRegisterForm>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-6">
                <input class="form-control" type="email" name="email" placeholder="some@some.com"/>
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