<#import "parts/common.ftl" as c>

<@c.page>
<#if isCurrentUser>
    <!-- Message Edit collapse Form -->
    <#include "parts/messageEdit.ftl" />
</#if>

    <!-- Message list -->
    <#include "parts/messageList.ftl" />
</@c.page>