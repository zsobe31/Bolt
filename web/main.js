function betolt(){
    $.ajax({
       url:"Controller",
       type:"post",
       data:{"task":"betoltVitamin"},
       success:function(valasz){
            var HTML = "";
            for (var i = 0; i < valasz.length; i++) {
                HTML += "<tr>\n\
                        <td>" + valasz[i].id + "</td>\n\
                        <td>" + valasz[i].nev + "</td>\n\
                        <td>" + valasz[i].leiras + "</td></tr>";
            }
            $("#tablazat-tbody").append(HTML);
       },
       error:function(){
           alert("hiba");
       }
    });
}

$(document).ready(function(){
    $("#tablazat-kereses").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#tablazat tbody tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });
});

function ujVitamin(){
    var a = document.getElementById("uj-nev").value;
    var b = document.getElementById("uj-leiras").value;
    $.ajax({
       url:"Controller",
       type:"post",
       data:{"task":"ujVitaminFelvitele", "nev":a, "leiras":b},
       success:function(valasz){
            valasz.success;
       },
       error:function(){}
    });
    setTimeout(function()
    { location.reload(true); }, 1000);
}

function torolVitamin(){
    var a = document.getElementById("torles-id").value;
    $.ajax({
       url:"Controller",
       type:"post",
       data:{"task":"torolVitaminid", "id":a},
       success:function(valasz){
            valasz.success;
       },
       error:function(){}
    });
    setTimeout(function()
    { location.reload(true); }, 1000);
}

function modVitamin(){
    var a = document.getElementById("mod-id").value;
    var b = document.getElementById("mod-nev").value;
    var c = document.getElementById("mod-leiras").value;
    $.ajax({
       url:"Controller",
       type:"post",
       data:{"task":"modositVitamin", "nev":b, "leiras":c, "id": a},
       success:function(valasz){
            valasz.success;
       },
       error:function(){}
    });
    setTimeout(function()
    { location.reload(true); }, 1000);
}