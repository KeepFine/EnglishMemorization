/*
 Template Name: Zegva - Responsive Bootstrap 4 Admin Dashboard
 Author: Themesdesign
 Website: www.themesdesign.in
 File: Alertify init js
 */

"use strict";

function chack(){
    $.post(
        {
            url:"/ajax/chackName",
            data:{"userCode":$("#userCode").val()},
            dataType:"json",
            success:function (data){
                var inf = data;
                var inf1 = JSON.stringify(inf)
                if(inf1 == "\"用户名重复\""){
                    $('#userinfo').css("color","red")
                }else {
                    $('#userinfo').css("color","green")
                }
                $("#userinfo").html(data.toString());
            }
        }
    )

}

function chackpwd(){
    $.post(
        {
            url:"/ajax/chackPwd",
            data:{"userPassword":$("#userPassword").val()},
            dataType:"json",
            success:function (data){
                var inf = data;
                var inf1 = JSON.stringify(inf)
                if(inf1 == "\"密码太简单了哦\""){
                    $('#userinfo2').css("color","green")
                }else {
                    $('#userinfo2').css("color","green")
                }
                $("#userinfo2").html(data.toString());
            }
        }
    )

}

(function() {

    function $(selector) {
        return document.querySelector(selector);
    }

    function reset (ev) {
        ev.preventDefault();
        alertify.reset();
    }

    function logDemo(selector) {
        (ga || function() { })("send", "event", "button", "click", "demo", selector);
    }

    function demo(selector, cb) {
        var el = $(selector);
        if(el) {
            el.addEventListener("click", function(ev) {
                ev.preventDefault();
                logDemo(selector);
                cb();
            });
        }
    }

    var ga = ga || function() {};

    // ==============================
    // Standard Dialogs
    demo("#alertify-alert", function (ev) {
        alertify.alert("This is an alert dialog");
        return false;
    });


    demo("#alertify-confirm", function (ev) {


        alertify.confirm("更换词库会导致记录清除，是否更换", function (ev) {
            ev.preventDefault();

            jQuery.post({
                url:"ajax/updataBook",
                data:{"bookId":"levelexbook"}
            })

            alertify.success("更换成功");
            location.reload([true])
        }, function(ev) {
            ev.preventDefault();
            alertify.error("取消更换");
        });
    });
    demo("#alertify-confirm2", function (ev) {
        alertify.confirm("更换词库会导致记录清除，是否更换", function (ev) {
            ev.preventDefault();
            alertify.success("更换成功");
        }, function(ev) {
            ev.preventDefault();
            alertify.error("取消更换");
        });
    });
    demo("#alertify-confirm3", function (ev) {
        alertify.confirm("更换词库会导致记录清除，是否更换", function (ev) {
            ev.preventDefault();
            alertify.success("更换成功");
        }, function(ev) {
            ev.preventDefault();
            alertify.error("取消更换");
        });
    });
    demo("#alertify-confirm4", function (ev) {
        alertify.confirm("更换词库会导致记录清除，是否更换", function (ev) {
            ev.preventDefault();
            alertify.success("更换成功");
        }, function(ev) {
            ev.preventDefault();
            alertify.error("取消更换");
        });
    });
    demo("#alertify-confirm5", function (ev) {
        alertify.confirm("是否更换练习", function (ev) {
            ev.preventDefault();
            jQuery.post({
                url:"ajax/updataPraBook",
                data:{"bookId":"errorbook"}
            })
            alertify.success("更换成功");
            location.reload([true])
        }, function(ev) {
            ev.preventDefault();
            alertify.error("取消更换");
        });


    });
    demo("#alertify-confirm6", function (ev) {
        alertify.confirm("是否更换练习", function (ev) {
            ev.preventDefault();
            jQuery.post({
                url:"ajax/updataPraBook",
                data:{"bookId":"learned"}
            })
            alertify.success("更换成功");
            location.reload([true])
        }, function(ev) {
            ev.preventDefault();
            alertify.error("取消更换");
        });
    });
    demo("#alertify-confirm7", function (ev) {
        alertify.confirm("是否更换练习", function (ev) {
            ev.preventDefault();
            jQuery.post({
                url:"ajax/updataPraBook",
                data:{"bookId":"learning"}
            })
            alertify.success("更换成功");
            location.reload([true])
        }, function(ev) {
            ev.preventDefault();
            alertify.error("取消更换");
        });
    });
    demo("#alertify-confirm8", function (ev) {
        alertify.confirm("是否删除", function (ev) {
            ev.preventDefault();
            alertify.success("删除成功");
        }, function(ev) {
            ev.preventDefault();
            alertify.error("取消删除");
        });
    });
    demo("#alertify-confirm9", function (ev) {
        alertify.confirm("是否删除", function (ev) {
            ev.preventDefault();
            alertify.success("删除成功");
        }, function(ev) {
            ev.preventDefault();
            alertify.error("取消删除");
        });
    });


    demo("#alertify-click-to-close", function (ev) {
        alertify
            .closeLogOnClick(true)
            .log("Click me to close!");
    });

    demo("#alertify-disable-click-to-close", function (ev) {
        alertify
            .closeLogOnClick(true)
            .log("Click me to close!")
            .closeLogOnClick(false)
            .log("You can't click to close this!");
    });

    demo("#alertify-reset", function (ev) {
        alertify
            .okBtn("Go For It!")
            .reset(ev)
            .alert("Custom values were reset");
    });

    demo("#alertify-log-template", function (ev) {
        alertify
            .setLogTemplate(function (input) { return 'log message: ' + input; })
            .log("This is the message");
    });

    demo("#alertify-max-log-items", function (ev) {
        alertify
            .maxLogItems(1)
            .log("This is the first message");

        // The timeout is just for visual effect.
        setTimeout(function() {
            alertify.log("The second message will force the first to close.");
        }, 1000);
    });

    demo("#alertify-prompt", function (ev) {
        alertify
            .defaultValue("Default value")
            .prompt("This is a prompt dialog", function (str, ev) {
                ev.preventDefault();
                alertify.success("You've clicked OK and typed: " + str);
            }, function(ev) {
                ev.preventDefault();
                alertify.error("You've clicked Cancel");
            });
    });

    // ==============================
    // Ajax
    demo("#alertify-ajax", function (ev) {
        alertify.confirm("Confirm?", function(ev) {
            ev.preventDefault();
            alertify.alert("Successful AJAX after OK");
        }, function(ev) {
            ev.preventDefault();
            alertify.alert("Successful AJAX after Cancel");
        });
    });

    // ==============================
    // Promise Aware
    demo("#alertify-promise", function (ev) {
        if ("function" !== typeof Promise) {
            alertify.alert("Your browser doesn't support promises");
            return;
        }

        alertify.confirm("Confirm?").then(function (resolvedValue) {
            // The click event is in the
            // event variable, so you can use
            // it here.
            resolvedValue.event.preventDefault();
            alertify.alert("You clicked the " + resolvedValue.buttonClicked + " button!");
        });
    });

    // ==============================
    // Standard Dialogs
    demo("#alertify-notification", function (ev) {
        alertify.log("Standard log message");
    });

    demo("#alertify-notification-html", function (ev) {
        alertify.log("<img src='https://placehold.it/256x128'><h3 class='font-18'>This is HTML</h3>");
    });

    demo("#alertify-notification-callback", function(ev) {
        alertify.log("Standard log message with callback", function(ev) {
            ev.preventDefault();
            alertify.log("You clicked the notification");
        });
    });

    demo("#alertify-success", function (ev) {

        if ("msg" === "注册成功") {
            alertify.success("注册成功");

        }
        else if ("msg" === "注册失败") {
            alertify.error("注册失败,该帐号已被注册");

        }
    });

    demo("#alertify-success-callback", function(ev) {
        alertify.success("Standard log message with callback", function() {
            alertify.success("You clicked the notification");
        });
    });

    demo("#alertify-error", function (ev) {
        alertify.error("Error log message");
    });

    demo("#alertify-error-callback", function(ev) {
        alertify.error("Standard log message with callback", function(ev) {
            ev.preventDefault();
            alertify.error("You clicked the notification");
        });
    });

    // ==============================
    // Custom Properties
    demo("#alertify-delay", function (ev) {
        alertify
            .delay(10000)
            .log("Hiding in 10 seconds");
    });

    demo("#alertify-forever", function (ev) {
        alertify
            .delay(0)
            .log("Will stay until clicked");
    });

    demo("#alertify-labels", function (ev) {
        alertify
            .okBtn("Accept")
            .cancelBtn("Deny")
            .confirm("Confirm dialog with custom button labels", function (ev) {
                ev.preventDefault();
                alertify.success("You've clicked OK");
            }, function(ev) {
                ev.preventDefault();
                alertify.error("You've clicked Cancel");
            });
    });

    demo("#alertify-log-position", function() {
        alertify.delay(1000); // This is just to make the demo go faster.
        alertify.log("Default bottom left position");
        setTimeout(function() {
            alertify.logPosition("top left");
            alertify.log("top left");
        }, 1500);
        setTimeout(function() {
            alertify.logPosition("top right");
            alertify.log("top right");
        }, 3000);
        setTimeout(function() {
            alertify.logPosition("bottom right");
            alertify.log("bottom right");
        }, 4500);
        setTimeout(function() {
            alertify.reset(); // Puts the message back to default position.
            alertify.log("Back to default");
        }, 6000);
    });

})();