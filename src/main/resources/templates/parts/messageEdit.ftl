<!-- Collapse new message form -->
<button class="btn btn-primary mb-2" type="button" data-toggle="collapse" data-target="#collapseNewMessage" aria-expanded="false" aria-controls="collapseNewMessage">
    Message Edit Form
</button>
<div class="collapse <#if message??>show</#if>" id="collapseNewMessage">
    <div class="form-group">
        <form method="POST" enctype="multipart/form-data" class="form">
            <div class="form-row">
                <div class="form-group col-sm-10">
                    <input type="text" placeholder="Message text" class="form-control mb-2 mr-sm-2 ${(textError??)?string('is-invalid','')}"
                           name="text" value="<#if message??>${message.text}</#if>" />
                    <#if textError??><div class="invalid-feedback">${textError}</div></#if>
                </div>
                <div class="form-group col-sm-2">
                    <input type="text" placeholder="tag" class="form-control mb-2 mr-sm-2 ${(tagError??)?string('is-invalid','')}"
                           name="tag"  value="<#if message??>${message.tag}</#if>" />
                    <#if tagError??><div class="invalid-feedback">${tagError}</div></#if>
                </div>
            </div>

            <div class="custom-file mb-2 mr-sm-2">
                <input type="file" name="file"  class="custom-file-input" id="customFile">
                <label class="custom-file-label" for="customFile">Choose file</label>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="<#if message??>${message.id}</#if>" />
            <button type="submit" class="btn btn-primary mb-2">Save message</button>
        </form>
    </div>
</div>