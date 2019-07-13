<#import "parts/common.ftl" as c>

<@c.page>
    <label class="col-sm-12">${message?ifExists}</label>
    <h5>${username}</h5>
    <form method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-6">
                <input class="form-control" type="password" name="password"/>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-6">
                <!-- email!'' означает что если емейл не указан, будет подставлена пустая строка,
                потому что у пользователя не обязательно может быть емейл, и тогда без !'' щаблон упадет -->
                <input class="form-control" type="email" name="email" placeholder="${email!''}" value="${email!''}"/>
            </div>
        </div>

        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit" class="btn btn-primary">Save</button>
    </form>
 </@c.page>