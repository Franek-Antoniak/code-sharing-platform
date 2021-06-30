function send() {
    let object = {
        "code": document.getElementById("code_snippet").value,
        "time": document.getElementById("time_restriction").value,
        "views": document.getElementById("views_restriction").value
    };

    let json = JSON.stringify(object);
    console.log(json);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/code/new', false);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
    if (xhr.status === 200) {
        let sizer = xhr.responseText.length;
        alert("Your UUID: " + xhr.responseText.substring(5, sizer - 1));
    }
}

