<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <label class="col-sm-12">${message?ifExists}</label>
    <@l.login "/login" false/>

</@c.page>