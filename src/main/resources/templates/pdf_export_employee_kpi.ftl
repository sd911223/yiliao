<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Title</title>
  <style>
    @page {
      size: 279mm 216mm;
    }
    * {
      padding: 0;
      border: 0;
      margin: 0;
    }
    .main {
      position: relative;
      width: 100%;
      padding: 78px 0;
      margin-bottom: 3px;
      background-color: #ebeff2;
    }
    .box {
      position: relative;
      width: 1240px;
      padding: 20px 53px 100px;
      margin: 0 auto;
      box-sizing: border-box;
      background-color: #fff;
    }
    .box .top {
      position: relative;
    }
    .box .top h3 {
      position: relative;
      padding: 40px 100px;
      font-weight: normal;
      font-size: 28px;
      letter-spacing: 2px;
      color: #3f3f3f;
    }
    .box .top h3 .logo {
      position: absolute;
      top: 50%;
      left: 0;
      width: 80px;
      height: 118px;
      margin-top: -59px;
      background: url(../templates/assets/report-logo.png) no-repeat center center;
      background-size: 100% auto;
    }
    .box .top .doctor {
      position: absolute;
      top: 0;
      right: -33px;
      width: 100%;
      text-align: right;
      font-size: 20px;
      color: #656565;
    }
    .box .top .doctor span {
      display: inline-block;
      margin-left: 10px;
    }
    /*  */
    .query-details {
      position: relative;
      max-width: 80%;
      margin: 0 auto;
    }
    .query-details > h4 {
      line-height: 40px;
      padding: 30px;
      text-align: center;
      font-size: 36px;
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
      font-size: 20px;
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
      font-size: 28px;
    }
    .query-details .block .title span {
      position: relative;
      margin-left: 20px;
      padding-left: 20px;
      letter-spacing: 2px;
      font-size: 20px;
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
    /*  */
    .block {
      margin-top: 60px;
      margin-bottom: 30px;
    }
    .block .title em {
      font-size: 26px;
    }
    .block1 .base-info {
      position: relative;
      width: 100%;
      list-style: none;
    }
    .block1 .base-info li {
      display: flex;
      padding: 20px;
      font-size: 20px;
      color: #656565;
    }
    .block1 .base-info li span {
      position: relative;
      width: 270px;
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
      flex: 1;
      padding-left: 74px;
    }
    .block1 .base-info li:nth-of-type(2n-1) {
      background-color: #ebeff2;
    }
    .block2 .result {
      position: relative;
      width: 100%;
      font-size: 20px;
      color: #656565;
    }
    .block2 .result span {
      position: relative;
      display: block;
      padding: 20px 60px;
    }
    .block2 .result span:nth-of-type(2n-1) {
      background-color: #ebeff2;
    }
    .block2 .result span .danger {
      position: absolute;
      top: 50%;
      left: 23px;
      width: 22px;
      height: 22px;
      margin-top: -11px;
      background: url(../templates/assets/report-01.png) no-repeat center center;
      background-size: 100% 100%;
    }
    .block3 .table, .block5 .table, .block6 .table {
      position: relative;
      width: 100%;
      margin-bottom: 30px;
    }
    .block3 .table dt, .block3 .table dd,
    .block5 .table dt, .block5 .table dd,
    .block6 .table dt, .block6 .table dd {
      position: relative;
      display: flex;
      background-color: #c7c7c7;
    }
    .block3 .table dt span, .block3 .table dd span,
    .block5 .table dt span, .block5 .table dd span,
    .block6 .table dt span, .block6 .table dd span {
      flex: 1;
      padding: 20px 0;
      text-align: center;
      font-size: 20px;
      color: #fff;
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
      width: 880px;
      margin: 56px auto 120px;
      list-style: none;
    }
    .diseases-info li {
      position: relative;
      display: block;
      width: 100%;
      height: 68px;
      border-top-left-radius: 70px;
      border-bottom-left-radius: 10px;
      border-top-right-radius: 10px;
      border-bottom-right-radius: 70px;
      margin-bottom: 4px;
      background-color: #ebeff2;
    }
    .diseases-info li i {
      position: absolute;
      top: 0;
      left: 0;
      display: block;
      width: 318px;
      height: 68px;
      line-height: 68px;
      border-top-left-radius: 70px;
      border-bottom-left-radius: 10px;
      border-top-right-radius: 10px;
      border-bottom-right-radius: 10px;
      text-align: center;
      font-style: normal;
      font-size: 22px;
      color: #fff;
    }
    .diseases-info li span {
      display: inline-block;
      width: 100%;
      line-height: 68px;
      padding-left: 386px;
      padding-right: 70px;
      box-sizing: border-box;
      font-size: 20px;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
    .block4 .diseases-info li:nth-of-type(1) i {
      background-color: #3bcaff;
    }
    .block4 .diseases-info li:nth-of-type(2) i {
      background-color: #3bbcff;
    }
    .block4 .diseases-info li:nth-of-type(3) i {
      background-color: #3ba5ff;
    }
    .block4 .diseases-info li:nth-of-type(4) i {
      background-color: #3b8eff;
    }
    .block7 .description {
      width: 1108px;
      line-height: 30px;
      padding-bottom: 40px;
      margin: 0 auto 50px;
      text-indent: 25px;
      font-size: 20px;
      color: #000;
    }
  </style>
</head>
<body>
  <div class="main">
    <div class="box query-details">
      <div class="top">
        <h3><i class="logo"></i>张三的全外解读报告</h3>
        <p class="doctor">
          <span>解读医生：李医生</span>
          <span>时间：2020/04/24</span>
        </p>
      </div>
      <div class="block block1">
        <p class="title"><em>患者基本信息</em></p>
        <ul class="base-info">
          <li><span>姓名：</span><p>张三</p></li>
          <li><span>性别：</span><p>女</p></li>
          <li><span>年龄：</span><p>20</p></li>
          <li><span>症状：</span><p>脚疼；头疼</p></li>
          <li><span>家族遗传病史：</span><p>无</p></li>
        </ul>
      </div>
      <div class="block block2">
        <p class="title"><em>检测结果</em></p>
        <p class="result">
          <span><i class="danger"></i>检测到3个高度关注的突变。突变Chr15:48487333 C>G 极有可能会导致色盲。</span>
          <span>检测到4个中度关注的突变。突变Chr2 8888 A>C 可能导致耳聋。</span>
          <span>检测到5个低度关注的突变。突变Chr3 8888 A>C 可能导致地贫。</span>
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
          <dd>
            <span>rs140598</span>
            <span>15:48487333</span>
            <span>C</span>
            <span>G</span>
            <span>0/1</span>
            <span>0.05</span>
            <span>CHRNB1</span>
            <span>0.85</span>
          </dd>
        </dl>
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
          <dd>
            <span>rs140598</span>
            <span>15:48487333</span>
            <span>C</span>
            <span>G</span>
            <span>0/1</span>
            <span>0.05</span>
            <span>CHRNB1</span>
            <span>0.85</span>
          </dd>
        </dl>
      </div>
      <div class="block block4">
        <p class="title"><em>重点关注疾病</em></p>
        <ul class="diseases-info">
          <li><i>疾病名</i><span>DIGEORGE SYNDROME; DGS</span></li>
          <li><i>发病年龄</i><span>Neonata</span></li>
          <li><i>遗传方式</i><span>Autosomal dominant</span></li>
          <li><i>发病率</i><span>1-5 / 10 000</span></li>
        </ul>
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
          <dd>
            <span>rs140598</span>
            <span>15:48487333</span>
            <span>C</span>
            <span>G</span>
            <span>0/1</span>
            <span>0.05</span>
            <span>CHRNB1</span>
            <span>0.85</span>
          </dd>
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
          <dd>
            <span>rs140598</span>
            <span>15:48487333</span>
            <span>C</span>
            <span>G</span>
            <span>0/1</span>
            <span>0.05</span>
            <span>CHRNB1</span>
            <span>0.85</span>
          </dd>
        </dl>
      </div>
      <div class="block block7">
        <p class="title"><em>参考文献</em></p>
        <p class="description">
        [1]  Ammann, A. J., Wara, D. W., Cowan, M. J., Barrett, D. J., Stiehm, E. R. The DiGeorge syndrome and the fetal alcohol syndrome. Am. J. Dis. Child. 136: 906-908, 1982.
        </p>
      </div>
    </div>
  </div>
</body>
</html>