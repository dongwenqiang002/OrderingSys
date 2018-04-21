$(function () {
/** 设置样式 */
    $(".regForm  >div").addClass("form-group ");

    $(".regForm  >div >div >input").attr({
        "class": "form-control  ",
        "data-toggle": "tooltip",
        "data-placement": "bottom",
        "data-trigger": "manual"
    });
    $(".regForm  >div >div").each(function () {
        var sp = $("  <span class=\"input-group-addon \" ><span " +
            "class=\"glyphicon\"></span></span>");
        var t = $(this).attr("t");
        if (t != null) {
            sp.children().addClass($(this).attr("t"));
            $(this).prepend(sp);
        }

    });
   // $('.regForm  div  input:even').tooltip('show');

    /** 点击下一步后*/
    $("#regsub").click(function () {
        if(!eamilReg()){
            return;
        }
        var a =false;
        $.get({url:"/user/regCodeVer?code="+$("input[name='code']").val(),async:false},function (result) {
            a=result;});
        if(!a){
            alert("验证码不正确");
            return;}
        $("#div1").animate({right: '800px'}, 1000, function () {
            $(this).hide();
            $("#div2").fadeIn(1900);
        });
    });

    $("#the_last_step").click(function () {
        $("#div2").animate({right: '800px'}, 1000, function () {
            $(this).hide();
            $("#div1").show();
            $("#div1").animate({right: '0px'});
            $("#div2").animate({right: '0px'});
            //$("#div1").fadeIn(1900);
        });
    });

    var height = $(window).height() - 50;//$("header").height()//-$("footer").height();
    console.log($("header").height());
    $(".left_vox").height(height);
    $(window).resize(function () {
        var height = $(window).height() - 50;
        $(".left_vox").height(height);
    });


    $("#sub_all").click(function () {
        var f1 = $("#form1").serialize();
        var f2 = $("#form2").serialize();
        console.log(f1);
        console.log(f2);
        $.post("/user/reg",f1+"&"+f2,function(d) {
            if(d === 'index'){
                alert("注册成功");
                location.href="/index";
            }else{
                alert("信息有误")
            }
        });
        //$("#all_form").submit();
    });

});