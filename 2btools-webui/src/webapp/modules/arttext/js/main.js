$(function(){

    $("#renderBtn").click(function(){
        //$.colorbox({opacity:1,href:"img/beike.png"});    
        $.colorbox({opacity:1,photo:true,href:"artword/generate.do?imgInfo.content=中国的孩子有点娇气，真是的娇气&imgInfo.filterName=zhujian_maoti"});    
        //$.colorbox({opacity:1,href:"http://localhost:8080/hello.jpg"});    
    });
    /**
     *注册文本输入框的点击事件
     *TODO chrome 浏览器需要点击第二次才能获取光标
     */
    $("#artTextArea").click(function(){
        if($.trim($("#artTextArea div").html())=="请输入要美化的文本"){
            $("#artTextArea").html("&nbsp;&nbsp;"); 
        }
        $("#artTextArea").focus(); 
    });

    /**
     * 注册菜单自动移动的事件
     *
     * */
    $(window).scroll(function(){
        var textAreaHeight=parseInt($("#artTextArea").css("height").replace(/(px)/g,""));
        var menuPanelHeight=parseInt($("#artTextMenuPanel").css("height").replace(/(px)/g,""));
        if(textAreaHeight>menuPanelHeight&&$(document).scrollTop()>150){
            $("#menuAlignPanel").css("margin-top",$(document).scrollTop()-100); 
        }else{
            $("#menuAlignPanel").css("margin-top",40); 
        }
    }); 

    /**
     * 注册纸张类型选择事件
     */
    $("#paperType li").each(function(){
        var select=function(){
            $("#paperType ul li[class='on']").attr("class",0);
            $(this).attr("class","on");
        };
        $(this).click(select);
    });

    /**
     * 注册字体类型选择事件
     *
     */
    $("#fontFamily li").each(function(){
        var select=function(){
            $("#fontFamily ul li[class='on']").attr("class",0);
            $(this).attr("class","on");
        };
        $(this).click(select);
    });
});
