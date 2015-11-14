/*
    whatsapp: 1-809-978-5552
    email: yoloprogramo22@gmail.com
    kakaotalk: JoanVasquez
*/

/* global self */

(function () {
    self.Ajax = function (elements) {
        this.elements = elements;
        this.datas_to_send = {};
    };

    self.Ajax.prototype = {
        ajaxProccess: function () {
            var reference = this;

            if (this.elements["action"] === "login") {
                this.datas_to_send["email"] = this.elements["email"];
                this.datas_to_send["pass"] = this.elements["pass"];
                this.datas_to_send["action"] = this.elements["action"];
            } else if (this.elements["action"] === "logout") {
                this.datas_to_send["action"] = this.elements["action"];
            }

            $.ajax({
                data: reference.datas_to_send,
                url: "login.co",
                type: "POST",
                dataType: "json",
                encode: true,
                cache: false,
                success: function (data) {
                    if (data.hasOwnProperty("success")) {
                        window.location = "welcome.jsp";
                    } else if (data.hasOwnProperty("logout")) {
                        window.location = "index.jsp";
                    } else {
                        new Errors(data).displayError();
                    }
                }
            });
        }
    };
})();

(function () {
    self.Errors = function (elements) {
        this.elements = elements;
    };

    self.Errors.prototype = {
        displayError: function () {
            var errors;
            if (this.elements.hasOwnProperty("emailError")) {
                errors = $($(".error")[0]);
                $($(".form-group")[0]).addClass("has-error");
                errors.html(this.elements.emailError);
                errors.css("color", "red");
            } else {
                $($(".error")[0]).empty();
                $($(".form-group")[0]).removeClass("has-error");
            }

            if (this.elements.hasOwnProperty("passError")) {
                errors = $($(".error")[1]);
                $($(".form-group")[1]).addClass("has-error");
                errors.html(this.elements.passError);
                errors.css("color", "red");
            } else {
                $($(".error")[1]).empty();
                $($(".form-group")[1]).removeClass("has-error");
            }

            if (this.elements.hasOwnProperty("loginError")) {
                errors = $($(".error")[2]);
                $($(".form-group")[2]).addClass("has-error");
                errors.html(this.elements.loginError);
                errors.css("color", "red");
            } else {
                $($(".error")[2]).empty();
                $($(".form-group")[2]).removeClass("has-error");
            }
        }
    };
})();

$(document).on("ready", function () {

    $("#login-form").on("submit", function (e) {
        e.preventDefault();
        var elements = [];
        elements["email"] = ($($("#login-form :input")[0]).val());
        elements["pass"] = ($($("#login-form :input")[1]).val());
        elements["action"] = "login";
        new Ajax(elements).ajaxProccess();
    });

    $("#log-out").on("click", function () {
        var elements = [];
        elements["action"] = "logout";
        new Ajax(elements).ajaxProccess();
    });
});