String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim = function () {
    return this.replace(/(^\s*)/g, "");
}
String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/g, "");
}

function isInt(str) {
    str = str.trim();
    return (/^(\+|-)?\d+$/.test(str));
}

function isNumber(str) {
    str = str.trim();
    return (/^\d+$/.test(str));
}

function isFloat(str) {
    str = str.trim();
    return (/^(\+|-)?\d+($|\.\d+$)/.test(str));
}

function isUnsignedFloat(str) {
    str = str.trim();
    var re = /^\d+(\.\d+)?$/;
    return (re.test(str));
}

function isCardId(str) {
    str = str.trim();
    var re = /^\d+$/;

    return (re.test(str));
}

function injectChar(str) {
//  var inj_str = "\"|'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
    var inj_str = "\" ' * % ; \\ --";
    var inj_stra = inj_str.split(" ");
    var inj = "";
    for (var i = 0; i < inj_stra.length; i++) {
        if (str.indexOf(inj_stra[i]) >= 0) {
            inj = inj_stra[i];
            return inj;
        }
    }

    return inj;
}

function injectInput(form1) {
    var inj = "";
    for (var i = 0; i < form1.elements.length; i++) {
        var e = form1.elements[i];

        if (e.type == "text") {
            if (e.value != null) {
                inj = injectChar(e.value);
                if (inj != "") {
                    if (e.disabled == false) {
                        e.focus();
                    }
                    return inj;
                }
            }
        }
    }

    return inj;
}

function isDate(str) {

    var re = /^((?!0000)[0-9]{4}-((0[1-9]|1[0-2])-(0[1-9]|1[0-9]|2[0-8])|(0[13-9]|1[0-2])-(29|30)|(0[13578]|1[02])-31)|([0-9]{2}(0[48]|[2468][048]|[13579][26])|(0[48]|[2468][048]|[13579][26])00)-02-29)$/;
    //alert (re.test(str));
    return (re.test(str));
}

function isTime(str) {
    var re = /^((((1[6-9]|[2-9]\d)\d{2})(?:\.|-|\/)(0?[13578]|1[02])(?:\.|-|\/)(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})(?:\.|-|\/)(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\d):[0-5]?\d:[0-5]?\d$/;
    return (re.test(str));
}

function imgFit(imageArr) {

    sWidth = imageArr.offsetWidth;
    sHeight = imageArr.offsetHeight;

    imageRate1 = (parseInt(window.screen.availWidth) / 4) * 3;
    imageRate2 = parseInt(imageArr.offsetHeight);
    //alert(imageRate1);

    imageArr.style.width = imageRate1 + "px";

    //alert(imageArr.offsetWidth+":"+imageArr.offsetHeight);
    //}
}

/*

function isIP(str)
{
	if (isNull(strIP)) return false;
	var re=/^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g //??IP????????
	if(re.test(strIP))
	{
		if( RegExp.$1 <256 && RegExp.$2<256 && RegExp.$3<256 && RegExp.$4<256) return true;
	}
	return false;
}

function isMobile(str)
{
	var regu =/^[1][3][0-9]{9}$/;
	var re = new RegExp(regu);
	return re.test(s));
}

function isEmail(str)
{
	var re = /^[-_A-Za-z0-9]+@([_A-Za-z0-9]+\.)+[A-Za-z0-9]{2,3}$/;
	return (re.test(str));
}
*/


function openWindowCenter(pageURL, w, h) {
    var l = eval(screen.Width - w) / 2;
    var t = eval(screen.Height - h) / 2;
    window.open(pageURL, "", "width=" + w + ",height=" + h + ",left=" + l + ",top=" + t + ",scrollbars=yes");
}

function exportData(pageURL) {
    var objBody = document.getElementsByTagName("body").item(0);
    var outframe = document.getElementById("outexcelframe");
    if (outframe == null || outframe == undefined) {
        try {
            outframe = document.createElement("<iframe id=\"outexcelframe\" style=\"display:none\">");
        } catch (ex) {
            outframe = document.createElement('iframe');
        }
        outframe.id = 'outexcelframe';
        outframe.width = 0;
        outframe.height = 0;
        outframe.marginHeight = 0;
        outframe.marginWidth = 0;
    }
    objBody.insertBefore(outframe, objBody.firstChild);
    outframe.src = pageURL;
}

/*form中如有disabled项，提交时对应的formbean中的值不生效，需要enabled*/
function setEnabled(eForm) {
    for (var i = 0; i < eForm.elements.length; i++) {
        var e = eForm.elements[i];
        if (e.type == "text" || e.type.substring(0, 6) == "select") {
            if (e.disabled == true) {
                e.disabled = false;
            }
        }
    }
}

/*将form中项disabled掉，用于浏览明细*/
function setDisabled(eForm) {
    for (var i = 0; i < eForm.elements.length; i++) {
        var e = eForm.elements[i];
        if (e.type == "text") {
            if (e.disabled == false) {
                e.disabled = true;
            }
        }
    }
}

function addEnterKey(f) {
    for (var i = 0; i < f.elements.length; i++) {
        var e = f.elements[i];
        if (e.type == "text" || e.type == "password" || e.type == "select" || e.type == "select-one") {
            e.onkeydown = function () {
                if (event.keyCode == 13) event.keyCode = 9;
            }
        }
    }
}

function do_exreport(currentPage) {
    var str = "<iframe id='upload_frame' name='upload_frame' src='' ></iframe>";
    var TemO = document.getElementById("frame");
    var newInput = document.createElement("iframe");
    newInput.id = "upload_frame";
    newInput.name = "upload_frame";
    newInput.style = "display:none";
    TemO.appendChild(newInput);
    var action = form1.action.value;
    var ttform = document.getElementById("form1");
    ttform.target = "upload_frame";
    ttform.action.value = "execl";
    ttform.listtype.value = action;
    ttform.submit();
    ttform.target = "";
    form1.action.value = action;
    document.getElementById("frame").removeChild(document.getElementById("upload_frame"));
}