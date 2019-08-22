<#include "security.ftl">

<div class="card-columns" id="message-list">
    <#list messages as message>
        <div class="card" data-id="${message.id}">
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top" />
            </#if>
            <div class="card-body">
                <h6 class="card-title">id: ${message.id}</h6>
                <p class="card-text">${message.text}</p>
                <i class="card-text">tag: #${message.tag}</i>
                <p class="card-text">
                    <small class="text-muted">
                        Author: <a href="/user-messages/${message.author.id}">${message.authorName}</a>
                        <#if message.author.id == currentUserId>
                            <a class="btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">
                                Edit
                            </a>
                        </#if>
                    </small>
                </p>
            </div>
        </div>
    <#else>
        No message
    </#list>
</div>