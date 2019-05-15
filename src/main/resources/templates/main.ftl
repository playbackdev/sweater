<#import "parts/common.ftl" as c>

<@c.page>
    <!-- Filter -->
    <div class="form-group">
        <form action="/main" method="GET" class="form-inline">
            <input type="text" name="filter" value="${filter?ifExists}" placeholder="Search by tag" class="form-control mb-2 mr-sm-2">
            <button type="submit" class="btn btn-primary mb-2">Search</button>
        </form>
    </div>
    <!-- Collapse new message form -->
    <button class="btn btn-primary mb-2" type="button" data-toggle="collapse" data-target="#collapseNewMessage" aria-expanded="false" aria-controls="collapseNewMessage">
        Add new message
    </button>
    <div class="collapse" id="collapseNewMessage">
        <div class="form-group">
            <form action="add" method="POST" enctype="multipart/form-data" class="form">
                <div class="form-row">
                    <div class="form-group col-sm-10">
                        <input type="text" name="text" placeholder="Message text" class="form-control mb-2 mr-sm-2">
                    </div>
                    <div class="form-group col-sm-2">
                        <input type="text" name="tag" placeholder="tag" class="form-control mb-2 mr-sm-2">
                    </div>
                </div>

                <div class="custom-file mb-2 mr-sm-2">
                    <input type="file" name="file"  class="custom-file-input" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                <button type="submit" class="btn btn-primary mb-2">Send</button>
            </form>
        </div>
    </div>
    <!-- Message list -->
    <div class="card-columns">
        <#list messages as message>
            <div class="card">
                <#if message.filename??>
                    <img src="/img/${message.filename}" class="card-img-top">
                </#if>
                <div class="card-body">
                    <h6 class="card-title">id: ${message.id}</h6>
                    <p class="card-text">${message.text}</p>
                    <i class="card-text">tag: ${message.tag}</i>
                    <p class="card-text"><small class="text-muted">Author: ${message.authorName}</small></p>
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>