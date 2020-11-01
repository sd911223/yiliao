<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
    <title>Title</title>
    <style>
        @page {
            size: 216mm 279mm;
        }

        * {
            font-family: SimSun;
            padding: 0;
            border: 0;
            margin: 0;
            /*color: #000;*/
        }

        .main {
            position: relative;
            display: block;
            width: 100%;
            /*padding: 78px 0;*/
            margin-bottom: 3px;
            /*background-color: #ebeff2;*/
        }

        .box {
            position: relative;
            width: 100%;
            /*padding: 20px 53px 100px;*/
            box-sizing: border-box;
            margin: 0 auto;
            background-color: #fff;
        }

        .box .top {
            position: relative;
        }

        .box .top h3 {
            position: relative;
            padding: 40px 100px;
            font-weight: normal;
            font-size: 24px;
            letter-spacing: 2px;
            color: #3f3f3f;
        }

        .box .top h3 .logo {
            position: absolute;
            top: 0;
            left: 0;
            width: 80px;
            height: 118px;
            background: url(../templates/images/report-logo.png) no-repeat center center;
            /* background: url(../../assets/report-logo.png) no-repeat center center; */
            background-size: 80% auto;
        }

        .box .top .doctor {
            position: absolute;
            top: 0;
            right: 0;
            width: 100%;
            text-align: right;
            font-size: 18px;
            color: #656565;
        }

        .box .top .doctor span {
            display: block;
        }

        /*  */
        .query-details {
            position: relative;
            max-width: 100%;
            margin: 0 auto;
        }

        .query-details > h4 {
            display: block;
            width: 100%;
            line-height: 40px;
            padding: 50px 30px 30px;
            box-sizing: border-box;
            text-align: center;
            font-size: 32px;
            color: #3f3f3f;
        }

        .query-details > h4 .tit {
            position: relative;
            padding: 0 80px;
        }

        .query-details > h4 .tit span {
            display: block;
            width: 100%;
        }

        .query-details > h4 .tit i {
            position: absolute;
            top: 0;
            right: 0;
            display: block;
            width: 80px;
            height: 40px;
            line-height: 40px;
            border-radius: 4px;
            text-align: center;
            font-style: normal;
            font-weight: normal;
            font-size: 15px;
            color: #fff;
            background-color: #3ba5ff;
            cursor: pointer;
        }

        .query-details > h4 .tit i:hover {
            background-color: #3bbcff;
        }

        .query-details > h4 .tit i.collected {
            background-color: #3ba5ff;
        }

        .query-details > h4 .tit i.collected:hover {
            background-color: #3ba5ff;
        }

        .query-details > h4 .subheading {
            line-height: 24px;
            padding-top: 10px;
            text-align: center;
            font-weight: normal;
            font-size: 18px;
            color: #606060;
        }

        .query-details .block {
            margin-top: 40px;
        }

        .query-details .block .title {
            position: relative;
            width: 100%;
            padding-bottom: 6px;
            border-bottom: 2px solid #c6f0ff;
            margin-bottom: 50px;
            color: #3bcaff;
        }

        .query-details .block .title em {
            display: inline-block;
            letter-spacing: 2px;
            font-style: normal;
            font-size: 24px;
        }

        .query-details .block .title span {
            position: relative;
            margin-left: 20px;
            padding-left: 20px;
            letter-spacing: 2px;
            font-size: 18px;
            vertical-align: middle;
        }

        .query-details .block .title span:before {
            position: absolute;
            top: -10px;
            left: 0;
            width: 2px;
            height: 34px;
            background-color: #3bcaff;
            content: '';
        }

        .block {
            margin-top: 60px;
            margin-bottom: 30px;
        }

        .block .title em {
            font-size: 22px;
        }

        .block1 .base-info {
            position: relative;
            width: 100%;
            list-style: none;
        }

        .block1 .base-info li {
            position: relative;
            display: block;
            min-height: 40px;
            padding: 10px 20px;
            font-size: 18px;
            color: #656565;
        }

        .block1 .base-info li span {
            position: absolute;
            top: 10px;
            left: 20px;
            width: 160px;
            height: 40px;
            line-height: 40px;
        }

        .block1 .base-info li span:after {
            position: absolute;
            top: 50%;
            right: 0;
            width: 2px;
            height: 40px;
            margin-top: -20px;
            background-color: #ccd0d4;
            content: '';
        }

        .block1 .base-info li p {
            position: relative;
            min-height: 40px;
            line-height: 40px;
            padding-left: 170px;
        }

        .block2 .result {
            position: relative;
            width: 100%;
            font-size: 18px;
            color: #656565;
        }

        .block2 .result span {
            position: relative;
            display: block;
            padding: 20px 60px;
        }

        .block2 .result span .danger {
            position: absolute;
            top: 20px;
            left: 23px;
            display: block;
            width: 22px;
            height: 22px;
            line-height: 22px;
            background: url(../templates/images/report-01.png) no-repeat center center;
            /* background: url(../../assets/report-01.png) no-repeat center center; */
            background-size: 100% 100%;
        }

        .block3 .table, .block5 .table, .block6 .table {
            position: relative;
            display: table;
            width: 100%;
            margin-bottom: 30px;
        }

        .block3 .table dt, .block3 .table dd,
        .block5 .table dt, .block5 .table dd,
        .block6 .table dt, .block6 .table dd {
            position: relative;
            display: table-row;
            width: 100%;
            background-color: #c7c7c7;
            word-wrap: break-word;
            word-break: break-all;
        }

        .block3 .table dt span, .block3 .table dd span,
        .block5 .table dt span, .block5 .table dd span,
        .block6 .table dt span, .block6 .table dd span {
            display: table-cell;
            padding: 14px 4px;
            /*height: 40px;*/
            /*line-height: 40px;*/
            text-align: center;
            font-size: 18px;
            vertical-align: middle;
            color: #fff;
            word-wrap: break-word;
            word-break: break-all;
        }

        .block3 .table dd,
        .block5 .table dd,
        .block6 .table dd {
            margin-top: 10px;
            background-color: #ebeff2;
        }

        .block3 .table dd span,
        .block5 .table dd span,
        .block6 .table dd span {
            font-size: 15px;
            color: #656565;
        }

        .diseases-info {
            position: relative;
            width: 100%;
            margin: 56px auto;
            list-style: none;
        }

        .diseases-info p {
            position: relative;
            display: block;
            width: 100%;
            height: 60px;
            border-top-left-radius: 70px;
            border-bottom-left-radius: 10px;
            border-top-right-radius: 10px;
            border-bottom-right-radius: 70px;
            margin-bottom: 6px;
            background-color: #ebeff2;
        }

        .diseases-info p i {
            position: absolute;
            top: 0;
            left: 0;
            display: block;
            width: 200px;
            height: 60px;
            line-height: 60px;
            border-top-left-radius: 70px;
            border-bottom-left-radius: 10px;
            border-top-right-radius: 10px;
            border-bottom-right-radius: 10px;
            text-align: center;
            font-style: normal;
            font-size: 22px;
            color: #fff;
        }

        .diseases-info p span {
            display: inline-block;
            width: 100%;
            line-height: 60px;
            padding-left: 220px;
            padding-right: 70px;
            box-sizing: border-box;
            font-size: 16px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }

        .block4 .diseases-info p:nth-of-type(1) i {
            background-color: #3bcaff;
        }

        .block4 .diseases-info p:nth-of-type(2) i {
            background-color: #3bbcff;
        }

        .block4 .diseases-info p:nth-of-type(3) i {
            background-color: #3ba5ff;
        }

        .block4 .diseases-info p:nth-of-type(4) i {
            background-color: #3b8eff;
        }

        .description {
            display: block;
            width: 100%;
            line-height: 30px;
            padding-bottom: 40px;
            text-indent: 25px;
            font-size: 18px;
            color: #000;
            word-wrap: break-word;
            word-break: break-all;
        }

        .gray {
            background-color: #ebeff2;
        }
    </style>
</head>
<body>
<div class="main">
    <div class="box query-details">
        <div class="top">
            <h3><i class="logo"></i>${patientName}的全外解读报告</h3>
            <p class="doctor">
                <span>解读医生：${doctor}</span>
                <span>时间：${statisticalTime}</span>
            </p>
        </div>
        <div class="block block1">
            <p class="title"><em>患者基本信息</em></p>
            <ul class="base-info">
                <li class="gray"><span>姓名：</span>
                    <p>
                        <#if patientName??>
                            ${patientName}
                        <#else>
                            无
                        </#if>
                    </p>
                </li>
                <li><span>性别：</span>
                    <p>
                        ${sex}
                    </p>
                </li>
                <li class="gray"><span>年龄：</span>
                    <p>
                        ${age}
                    </p>
                </li>
                <li><span>症状：</span>
                    <p>
                        ${symptom}
                    </p>
                </li>
                <li class="gray"><span>家族遗传病史：</span>
                    <p>
                        ${homeDisease}
                    </p>
                </li>
            </ul>
        </div>
        <div class="block block2">
            <p class="title"><em>检测结果</em></p>
            <p class="result">
                <span class="gray"><i class="danger"></i>检测到&nbsp;${heighList}&nbsp;个高度关注的突变。突变&nbsp;${heighResult}&nbsp;极有可能会导致&nbsp;${heighDisease}。</span>
                <span>检测到&nbsp;${moderateList}&nbsp;个中度关注的突变。突变&nbsp;${moderateResult}&nbsp;可能导致&nbsp;${moderateDisease}。</span>
                <span class="gray">检测到&nbsp;${lowList}&nbsp;个低度关注的突变。突变&nbsp;${lowResult}&nbsp;可能导致&nbsp;${lowDisease}。</span>
            </p>
        </div>
        <div class="block block3">
            <p class="title"><em>高度关注</em></p>
            <dl class="table">
                <dt>
                    <span>RS</span>
                    <span>染色体位置</span>
                    <span>Ref</span>
                    <span>Alt</span>
                    <span>基因型</span>
                    <span>MAF</span>
                    <span>基因</span>
                    <span>致病分值</span>
                </dt>
                <#if heighData?exists>
                    <#list heighData as map>
                        <dd>
                            <span>${map.variation}</span>
                            <span>${map.chromosomePosition}</span>
                            <span>${map.ref}</span>
                            <span>${map.alt}</span>
                            <span>${map.geneShape}</span>
                            <span>${map.maf}</span>
                            <span>${map.gene}</span>
                            <span>${map.pathogenicPoints}</span>
                        </dd>
                    </#list>
                </#if>
            </dl>
            <dl class="table">
                <dt>
                    <span>突变类型</span>
                    <span>蛋白变化</span>
                    <span>相关疾病</span>
                    <span>来源</span>
                    <span>文献</span>
                </dt>
                <#if heighData?exists>
                    <#list heighData as map>
                        <dd>
                            <span>${map.mutationType}</span>
                            <span>${map.proteinChange}</span>
                            <span>
                             <#if map.relatedDisease?exists>
                                 <#list map.relatedDisease?split("\t") as relatedDate>
                                     <#if relatedDate == "">
                                         -
                                     <#else>
                                         <#if relatedDate_index !=0 >
                                             ${relatedDate} <br/>
                                         </#if>
                                     </#if>
                                 </#list>
                             </#if>
                            </span>
                            <span>
                             <#if map.source?exists>
                                 <#list map.source?split("\t") as sourceDate>
                                     <#if sourceDate == "">
                                         -
                                     <#else>
                                         <#if sourceDate_index !=0 >
                                             ${sourceDate} <br/>
                                         </#if>
                                     </#if>
                                 </#list>
                             </#if>
                            </span>
                            <span>
                             <#if map.literature?exists>
                                 <#list map.literature?split("\t") as latelyDate>
                                     <#if latelyDate == "">
                                         -
                                     <#else>
                                         <#if latelyDate_index <3 >
                                             ${latelyDate} <br/>
                                         </#if>
                                     </#if>
                                 </#list>
                             </#if>
                                </span>
                        </dd>
                    </#list>
                </#if>
            </dl>
        </div>
        <div class="block block4">
            <p class="title"><em>重点关注疾病</em></p>
            <#if maps??>
                <div class="diseases-info">
                    <p><i style="background-color:#3bcaff">疾病名</i><span>${maps['disease_name']}</span></p>
                    <p><i style="background-color:#3bbcff">发病年龄</i><span>${maps['age_of_onset_orp']}</span></p>
                    <p><i style="background-color:#3ba5ff">遗传方式</i><span>${maps['inheritance_orp']}</span></p>
                    <p><i style="background-color:#3b8eff">发病率</i><span>${maps['prevalence_orp']?html}</span></p>
                </div>
            </#if>
        </div>
        <div class="block block5">
            <p class="title"><em>中度关注</em></p>
            <dl class="table">
                <dt>
                    <span>RS</span>
                    <span>染色体位置</span>
                    <span>Ref</span>
                    <span>Alt</span>
                    <span>基因型</span>
                    <span>MAF</span>
                    <span>基因</span>
                    <span>致病分值</span>
                </dt>
                <#if moderateData?exists>
                    <#list moderateData as map>
                        <dd>
                            <span>${map.variation}</span>
                            <span>${map.chromosomePosition}</span>
                            <span>${map.ref}</span>
                            <span>${map.alt}</span>
                            <span>${map.geneShape}</span>
                            <span>${map.maf}</span>
                            <span>${map.gene}</span>
                            <span>${map.pathogenicPoints}</span>
                        </dd>
                    </#list>
                </#if>
            </dl>
            <dl class="table">
                <dt>
                    <span>突变类型</span>
                    <span>蛋白变化</span>
                    <span>相关疾病</span>
                    <span>来源</span>
                    <span>文献</span>
                </dt>
                <#if moderateData?exists>
                    <#list moderateData as map>
                        <dd>
                            <span style="width: 20%">${map.mutationType}</span>
                            <span style="width: 20%">${map.proteinChange}</span>
                            <span style="width: 20%">
                             <#if map.relatedDisease?exists>
                                 <#list map.relatedDisease?split("\t") as relatedDate>
                                     <#if relatedDate == "">
                                     -
                                 <#else>
                                     <#if relatedDate_index !=0 >
                                         ${relatedDate} <br/>
                                     </#if>
                                 </#if>
                                 </#list>
                             </#if>
                            </span>
                            <span style="width: 20%">
                             <#if map.source?exists>
                                 <#list map.source?split("\t") as sourceDate>
                                     <#if sourceDate == "">
                                     -
                                 <#else>
                                     <#if sourceDate_index !=0 >
                                         ${sourceDate} <br/>
                                     </#if>
                                 </#if>
                                 </#list>
                             </#if>
                            </span>
                            <span style="width: 20%">
                             <#if map.literature?exists>
                                 <#list map.literature?split("\t") as latelyDate>                                                <#if latelyDate == "已过期">
                                     -
                                 <#else>
                                     <#if latelyDate_index <3 >
                                         ${latelyDate} <br/>
                                     </#if>
                                 </#if>
                                 </#list>
                             </#if>
                            </span>
                        </dd>
                    </#list>
                </#if>
            </dl>
        </div>
        <div class="block block6">
            <p class="title"><em>其他</em></p>
            <dl class="table">
                <dt>
                    <span>RS</span>
                    <span>染色体位置</span>
                    <span>Ref</span>
                    <span>Alt</span>
                    <span>基因型</span>
                    <span>MAF</span>
                    <span>基因</span>
                    <span>致病分值</span>
                </dt>
                <#if lowData?exists>
                    <#list lowData as map>
                        <dd>
                            <span>${map.variation}</span>
                            <span>${map.chromosomePosition}</span>
                            <span>${map.ref}</span>
                            <span>${map.alt}</span>
                            <span>${map.geneShape}</span>
                            <span>${map.maf}</span>
                            <span>${map.gene}</span>
                            <span>${map.pathogenicPoints}</span>
                        </dd>
                    </#list>
                </#if>
            </dl>
            <dl class="table">
                <dt>
                    <span>突变类型</span>
                    <span>蛋白变化</span>
                </dt>
                <#if lowData?exists>
                    <#list lowData as map>
                        <dd>
                            <span style="width: 20%">${map.mutationType}</span>
                            <span style="width: 20%">${map.proteinChange}</span>
                        </dd>
                    </#list>
                </#if>
            </dl>
        </div>
        <div class="block block7">
            <p class="title"><em>参考文献</em></p>
            <p class="description" style="word-wrap: break-word !important; word-break: break-all !important;">
                ${literature}
            </p>
        </div>
    </div>
</div>
</body>
</html>