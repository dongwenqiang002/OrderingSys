$(function () {

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
        sp.children().addClass($(this).attr("t"));
        $(this).prepend(sp);
    });
    $('.regForm  div  input:even').tooltip('show');

    $("#regsub").click(function () {
        $("#div1").animate({right: '500px'}, "2000",function () {
            $(this).hide();
            $("#div2").fadeIn(3000);
        });


    });

    var height = $(window).height()-50 ;//$("header").height()//-$("footer").height();
    console.log( $("header").height());
    $(".left_vox").height(height);
    $(window).resize(function() {
        var height = $(window).height()-50;
        $(".left_vox").height(height);
    });
});