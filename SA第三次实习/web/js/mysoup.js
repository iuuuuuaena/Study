
SOAP_sendOne = function (data) {

    var response;
    console.log("data值为：");
    console.log(data.title1);


    console.log(data.book1);
    console.log(data.book2);

    if (data.title1.indexOf(",") == -1){
    var book = "" +
        "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
        "    <Body>\n" +
        "        <sendOne xmlns=\"http://example\">\n" +
        "            <URL>" + data.title1+ "</URL>\n" +
        "            <nameOne>" + data.book1+ "</nameOne>\n" +
        "            <nametwo>"+ data.book2+ "</nametwo>\n" +
        "        </sendOne>\n" +
        "    </Body>\n" +
        "</Envelope>";

    console.log("DATA为:"+data.title1+data.book1+data.book2);

        $.ajax({
            async: true,
            crossDomain: true,
            url: "http://47.97.184.36:8080/services/post",
            method: "POST",
            headers: {
                "content-type": "text/xml",
                "soapaction": "\\\"\\\"",
                "cache-control": "no-cache",
            },
            data: book,
            type: "post",   //鐠囬攱鐪伴弬鐟扮础,

            beforeSend: function () {
                //鐠囬攱鐪伴崜宥囨畱婢跺嫮鎮�
            },
            success: function (data) {
                //鐠囬攱鐪伴幋鎰閺冭泛顦╅悶锟�
                response = data.getElementsByTagName("sendOneReturn")[0].childNodes[0].textContent;

                if (response === "success") {
                    alert("成功");
                }
                else {
                    alert("失败");
                }


            },
            error: function () {
                //鐠囬攱鐪伴崙娲晩婢跺嫮鎮�
                alert("Ajax Error")
            }
        });
    }else{
        var book = "" +
            "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
            "    <Body>\n" +
            "        <sendMany xmlns=\"http://example\">\n" +
            "            <URL>" + data.title1+ "</URL>\n" +
            "            <nameOne>" + data.book1+ "</nameOne>\n" +
            "            <nametwo>"+ data.book2+ "</nametwo>\n" +
            "        </sendMany>\n" +
            "    </Body>\n" +
            "</Envelope>";

        $.ajax({
            async: true,
            crossDomain: true,
            url: "http://47.97.184.36:8080/myPost2/services/manypost",
            method: "POST",
            headers: {
                "content-type": "text/xml",
                "soapaction": "\\\"\\\"",
                "cache-control": "no-cache",
            },
            data: book,
            type: "manypost",   //鐠囬攱鐪伴弬鐟扮础,

            beforeSend: function () {
                //鐠囬攱鐪伴崜宥囨畱婢跺嫮鎮�
            },
            success: function (data) {
                //鐠囬攱鐪伴幋鎰閺冭泛顦╅悶锟�
                response = data.getElementsByTagName("sendManyReturn");

                if (response == "success") {
                    alert("成功");
                }
                else {
                    alert("失败");
                }


            },
            error: function () {
                //鐠囬攱鐪伴崙娲晩婢跺嫮鎮�
                alert("Ajax Error")
            }
        });
    }

    return response;

};




SOAP_validateEmailAddress = function (data) {

    var response;

    var _payload = "" +
        "<Envelope xmlns=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
        "    <Body>\n" +
        "        <validateEmailAddress xmlns=\"http://post\">\n" +
        "            <_url>" + data.title1+data.title2 + "</_url>\n" +
        "        </validateEmailAddress>\n" +
        "    </Body>\n" +
        "</Envelope>";


    $.ajax({
        async: false,
        crossDomain: true,
        url: "http://47.97.184.36:8080/myPost2/services/post",
        method: "POST",
        headers: {
            "content-type": "text/xml",
            "soapaction": "\\\"\\\"",
            "cache-control": "no-cache",
        },
        data: data.book1+data.book2,
        type: "post",   //鐠囬攱鐪伴弬鐟扮础,

        beforeSend: function () {
            //鐠囬攱鐪伴崜宥囨畱婢跺嫮鎮�
        },
        success: function (data) {

            response = data.getElementsByTagName("validateEmailAddressReturn")[0].childNodes[0].textContent;
            if(response==="true")
            {
                alert("閭姝ｇ‘锛�");
            }
            else
            {
                alert("閭閿欒锛�");
            }

        },
        error: function () {
            //鐠囬攱鐪伴崙娲晩婢跺嫮鎮�
            alert("Ajax Error")
        }
    });

    return response;

};