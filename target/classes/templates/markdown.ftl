###生成markDown文档

## ${(name)!''}

### 接口描述
${(desc)!''}


### 接口地址
${(url)!''}

### 请求方式
${(httpMethod)!''}


### 请求参数
| **参数名称** | **类型**|
| --- | --- |
<#if requestParamList?exists && (requestParamList?size>0)>
    <@paramListCard requestParamList/>
</#if>

<#macro paramListCard paramList>
    <#list paramList as item>
        | ${(item.name)!''} | ${(item.type)!''}|
    </#list>
</#macro>