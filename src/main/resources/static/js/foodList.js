




/**点击添加购物车,左侧购物车出现*/
function addToCart(btn, name, id, price) {
    $(btn).parent().hide();
    console.log($(btn).parent().next());
    $(btn).parent().next().show();
    $(btn).parent().next().find("input").val(1);
    var li = "<li class='list-group-item' foodid='"+
        id+"'><div ><span >"+
        name+"</span><span><button " +
        "onclick='onclicksub("+id+",this)'>-</button><input value='1'><button " +
        "onclick='onclicksub("+id+",this)'>+</button></span><span>"+
        price+"</span></div></li>";
    $("#shoppingCart").append(li);
}

/**
 * 点击购物车提交
 * */
$("#submitOrder").click(function () {
    var order=new Array();
    /*var count = $("#shoppingCart >li input").val()
    console.log( count );*/
    $("#shoppingCart >li").each(function (i) {
        var food ={};
        $(this).attr('foodid');
        food.count = $(this).find("input").val();
        food.id=$(this).attr("foodif");
        order[i] = food;
    })
    $("button").click(function(){
        $.ajax({url:"demo_test.txt",success:function(result){
                $("#div1").html(result);
            }});
    });
    console.log(order)
});

/**
 * 点击添加和删除
 * */
function onclickadd(id,btn){
    console.log($(btn));
    console.log('点击++');
    var count = $(btn).siblings("input").val();
    $("[foodid='"+id+"'] input").val(parseInt(count)+1);
}
function onclicksub(id,btn){
    console.log('点击--');
    var count = $(btn).siblings("input").val();
    if(parseInt(count) == 1){
        $("#shoppingCart > [foodid='"+id+"']").remove();
        $("[foodid='"+id+"'] >div >div:eq(0)").show();
        $("[foodid='"+id+"'] >div >div:eq(1)").hide();
    }else
        $("[foodid='"+id+"']  input").val(parseInt(count)-1);
}