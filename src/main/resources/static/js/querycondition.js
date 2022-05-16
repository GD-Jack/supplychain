function getElementName(eDataValue,name) {
    var p;
    var eName = "";
    p = eDataValue.indexOf(".");
    if ((p != -1) && (eDataValue.length > p + 1)) {
        eName = eDataValue.substring(0,p) + "." + name;
    }
    return eName;
}

function addDoubleclick(f,contextPath) {
    for (var i = 0; i < f.elements.length; i++) {
        var e = f.elements[i];
        if (e.type == "text") {
            if (e.name.indexOf(".datavalue") != -1)  {
                e.ondblclick = function() {columndbclick(f,this,contextPath);}
            }
        }
    }
}
function columndbclick(f,e,contextPath) {
    var o = new Object();
    o.ret = "0";
    o.eDataValue = e.name;
    o.dataValue = e.value;
    o.eDataType = getElementName(o.eDataValue,"datatype");
    o.eMatch = getElementName(o.eDataValue,"match");
    o.eDataValue_more = getElementName(o.eDataValue,"datavalue_more");
    if ((o.eDataType != null) && (o.eDataType.length != 0)) {
        o.dataType = f.elements[o.eDataType].value;
    }
    else{
        o.dataType = "";
    }
    if ((o.eMatch != null) && (o.eMatch.length != 0)) {
        o.match = f.elements[o.eMatch].value;
    }
    else{
        o.match = "1";
    }
    if ((o.eDataValue_more != null) && (o.eDataValue_more.length != 0)) {
        o.dataValue_more = f.elements[o.eDataValue_more].value;
    }
    else{
        o.dataValue_more = "";
    }

    if ((o.dataType == "char") || (o.dataType == "varchar")) {
        if(window.showModalDialog == undefined){
            var winOption = "height=265,width=400,top=50,left=50,toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizable=yes,fullscreen=0";
            var reto = window.open(contextPath + "/scm_page/frame/charMatch.jsp",o,winOption)
        }
    else{
            var reto = window.showModalDialog(contextPath + "/scm_page/frame/charMatch.jsp",o,"dialogWidth=400px;dialogHeight=265px");
        }
        if (reto.ret == "1") {
            f.elements[o.eMatch].value = reto.match;

            if (reto.match == "7") {
                e.value = reto.dataValue;
                f.elements[o.eDataValue_more].value = reto.dataValue_more;
            }
            else
            {
                f.elements[o.eDataValue_more].value = "";
            }
        }
    }
    else {
        var reto = window.showModalDialog(contextPath + "/scm_page/frame/numMatch.jsp",o,"dialogWidth=400px;dialogHeight=290px");
        if (reto.ret == "1") {
            f.elements[o.eMatch].value = reto.match;
            if (reto.match == "7") {
                e.value = reto.dataValue;
                f.elements[o.eDataValue_more].value = reto.dataValue_more;
            }
            else
            {
                f.elements[o.eDataValue_more].value = "";
            }
        }
    }
}
function conditionDisplayChange(eButton,divCondition,eForm) {
    if (divCondition.style.display=="none") {
        eButton.value = "隐藏条件";
        divCondition.style.display="block";
        eForm.showcondition.value=true;
    }else {
        eButton.value = "显示条件";
        divCondition.style.display="none";
        eForm.showcondition.value=false;
    }
}

function conditionDisplay(eButton,divCondition,eForm) {
    if (eForm.showcondition.value=="true") {
        eButton.value = "隐藏条件";
        divCondition.style.display="block";
    }else {
        eButton.value = "显示条件";
        divCondition.style.display="none";
    }
}

function setSupplier(eSupplier,suppliercode) {
    if (suppliercode != null && suppliercode != "") {
        eSupplier.value = suppliercode;
        eSupplier.disabled = true;
    }
}
function setExport(divCondition,isexport) {
    if (isexport == "1") {
        document.getElementById(divCondition).style.display="inline";
    }
    else
    {

        document.getElementById(divCondition).style.display="none";
    }
}
function f1(s1)
{
    return "1234";
}
