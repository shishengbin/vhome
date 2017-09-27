var accountid=request("accountid");
var humanId=getCookie("humanId");
$("#allClass").attr("href","search.html?accountid="+accountid+"&humanId="+humanId);
$("#mine").attr("href","mine.html?humanId="+humanId+"&accountid="+accountid);
$("#cartdefault").attr("href","../indent/appCart?id="+humanId);
$("#foot_index").attr("href","index.html?accountid="+accountid+"&humanId="+humanId);
$("#foot_human").attr("href","mine.html?accountid="+accountid+"&humanId="+humanId);
$("#foot_company").attr("href","/vhome_r/ws/wxpages/brandinfo.html?accountid="+accountid);


