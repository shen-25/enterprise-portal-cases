<#--
${site!"这是空值"}
${time?string("yyyy年MM月dd日HH小时mm分ss秒")}
${number?string("0,00.00")}-->
${computer.sn}
${computer.model}
${computer.model?replace("hu", "____")}
${(computer.model?index_of("h") != -1)?string("有h", "没有h")}
<#-- 三目运算 -->

${computer.dop?string("yyyy年MM月dd日HH时mm分")}
${computer.price?string("0.0")}
${computer.price?floor}
----------------------------------------------------------
<#if (computer.price) gt 1000 >
你的电脑：好贵!!!
<#elseif computer.price lt 1000>
你的电脑：好便宜？？？？
<#elseif computer.price == 1000>
你的电脑：合适的价格...
</#if>

<#switch computer.sn>
   <#case "1111">
     111111111
     <#break>
   <#case "2222">
   222222222
   <#break>
   <#case "3333">
   33333333333
   <#break>
   <#default>
   没有匹配的字符串
</#switch>

<#list list as integer>
   <li>${integer_index + 1}-----${integer} <li>
</#list>
===============================
<#list m?keys as k>
    ${k}-${m[k]}
</#list>

