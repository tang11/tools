curl --location --request ${(httpMethod)!'POST'} '${(url)!''}' \
<#if headers?? && (headers?size>0)>
    <#list headers as key,value>
        --header '${(key)!''}: ${(value?is_string)?then(value,value?c)}' \
    </#list>
</#if>
<#if bodyJson??>
    --data-raw '${bodyJson}'
</#if>
