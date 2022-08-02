function switchValid(onOff, input, errSelector, message) {
    if (onOff == false) {
        $(errSelector).text(message);
        $(input).addClass("error_input");
        $(errSelector).addClass("error_message");
    } else {
        $(errSelector).text("");
        $(input).removeClass("error_input");
        $(errSelector).removeClass("error_message");
    }
}

function checkEmpty(input, errSelector) {
    var val = $(input).val();
    if ($.trim(val) == "") {
        switchValid(false, input, errSelector, "请输入内容");
        return false;
    } else {
        switchValid(true, input, errSelector);
        return true;
    }
}

function checkCategory(input, errSelector) {
    var value = $(input).val();
    if (value == -1) {
        switchValid(false, input, errSelector, "请选择油画类型");
        return false;
    } else {
        switchValid(true, input, errSelector)
        return true;
    }
}

function checkPrice(input, errSelector) {
    var val = $(input).val();
    var regex = /^[1-9][0-9]*$/;
    if (!regex.test(val)) {
        switchValid(false, input, errSelector, "价格无效");
    } else {
        switchValid(true, input, errSelector);
        return true;
    }
}

function checkFile(input, errSelector) {
    if (checkEmpty(input, errSelector) == false) {
        return false;
    }
    var val = $(input).val().toLowerCase();
    if (val.length < 4) {
        switchValid(false, input, errSelector, "请选择有效的图片");
    }
    suffix = val.substring(val.length - 3);
    if (suffix == 'jpg' || suffix == 'png' || suffix == 'gif') {
        switchValid(true, input, errSelector);
        return true;
    } else {
        switchValid(false, input, errSelector, "请选择有效的图片");
        return false;
    }
}