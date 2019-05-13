<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <div>
        <form action="add" method="POST" enctype="multipart/form-data">
            <input type="text" name="text" placeholder="Введите текст сообщения">
            <input type="text" name="tag" placeholder="Тэг">
            <input type="file" name="file">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Добавить</button>
        </form>
    </div>
    <div>Список сообщений</div>
    <form action="/main" method="GET">
        <input type="text" name="filter" value="${filter?ifExists}" placeholder="Что найти?">
        <button type="submit">Найти</button>
    </form>
    <#list messages as message>
    <div>
        <b>${message.id}</b>
        <span>${message.text}</span>
        <i>${message.tag}</i>
        <stong>${message.authorName}</stong>
        <div>
            <#if message.filename??>
                <img src="/img/${message.filename}"
            </#if>
        </div>
    </div>
    <#else>
        No message
    </#list>
    <div>
        <a href="/user">User List</a>
        <@l.logout />
    </div>
</@c.page>