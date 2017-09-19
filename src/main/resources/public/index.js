class MyForm{
    constructor(container){
        this.container = container;
    }

    getData(){
        return this.container.data.value;
    }

    submit(){
        this.sendRequest("/format");
    }

    sendRequest(location){
        var req = new XMLHttpRequest();
        req.open('POST', location, true);
        req.send(this.getData());

        var result = document.getElementById("result");
        req.onreadystatechange = function(){
            if(req.readyState === XMLHttpRequest.DONE && req.status === 200){
                result.innerHTML = req.responseText;
            }
        };
    }
}

form = document.getElementById("myForm");
myForm = new MyForm(form);

document.getElementById("compileButton").onclick = function(){
    myForm.submit();
}