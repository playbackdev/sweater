<#import "parts/common.ftl" as c>

<@c.page>
    <!-- Filter -->
    <div class="form-group">
        <form action="/main" method="GET" class="form-inline">
            <input type="text" name="filter" value="${filter?ifExists}" placeholder="Search by tag" class="form-control mb-2 mr-sm-2">
            <button type="submit" class="btn btn-primary mb-2">Search</button>
        </form>
    </div>

    <!-- Message Edit collapse Form -->
    <#include "parts/messageEdit.ftl" />
    <!-- Message list -->
    <#include "parts/messageList.ftl" />
</@c.page>